// Form validation utilities
function validateEmail(email) {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
}

function validatePhone(phone) {
    const re = /^[\+]?[1-9][\d]{0,15}$/;
    return re.test(phone.replace(/[\s\-\(\)]/g, ''));
}

function validateUrl(url) {
    if (!url) return true; // Optional field
    try {
        new URL(url);
        return true;
    } catch {
        return false;
    }
}

function showError(message) {
    const errorDiv = document.createElement('div');
    errorDiv.className = 'error-message';
    errorDiv.textContent = message;

    const container = document.querySelector('.container');
    const existingError = container.querySelector('.error-message');
    if (existingError) {
        existingError.remove();
    }

    container.insertBefore(errorDiv, container.firstChild);

    // Auto-hide after 5 seconds
    setTimeout(() => {
        if (errorDiv.parentNode) {
            errorDiv.remove();
        }
    }, 5000);
}

function showSuccess(message) {
    const successDiv = document.createElement('div');
    successDiv.className = 'success-message';
    successDiv.textContent = message;

    const container = document.querySelector('.container');
    const existingSuccess = container.querySelector('.success-message');
    if (existingSuccess) {
        existingSuccess.remove();
    }

    container.insertBefore(successDiv, container.firstChild);

    // Auto-hide after 3 seconds
    setTimeout(() => {
        if (successDiv.parentNode) {
            successDiv.remove();
        }
    }, 3000);
}

// Login form validation
function validateLoginForm() {
    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value;

    if (!username) {
        showError('Username is required');
        return false;
    }

    if (!password) {
        showError('Password is required');
        return false;
    }

    return true;
}

// School form validation with API validation
function validateSchoolForm() {
    const name = document.getElementById('name').value.trim();
    const registrationNumber = document.getElementById('registrationNumber').value.trim();
    const email = document.getElementById('email').value.trim();
    const phone = document.getElementById('phone').value.trim();
    const website = document.getElementById('website').value.trim();
    const district = document.getElementById('district').value.trim();
    const county = document.getElementById('county').value.trim();
    const parish = document.getElementById('parish').value.trim();
    const village = document.getElementById('village').value.trim();

    // Basic validation
    if (!name) {
        showError('School name is required');
        return false;
    }

    if (!registrationNumber) {
        showError('School registration number is required');
        return false;
    }

    if (!validateRegistrationNumber(registrationNumber)) {
        showError('Invalid school registration number format (e.g., UG123456)');
        return false;
    }

    if (!district) {
        showError('District is required');
        return false;
    }

    if (!county) {
        showError('County/Sub-county is required');
        return false;
    }

    if (!parish) {
        showError('Parish is required');
        return false;
    }

    if (!village) {
        showError('Village is required');
        return false;
    }

    if (!email) {
        showError('Email is required');
        return false;
    }

    if (!validateEmail(email)) {
        showError('Please enter a valid email address');
        return false;
    }

    if (!phone) {
        showError('Phone number is required');
        return false;
    }

    if (!validateUgandanPhone(phone)) {
        showError('Please enter a valid Ugandan phone number (+256XXXXXXXXX)');
        return false;
    }

    if (website && !validateUrl(website)) {
        showError('Please enter a valid website URL');
        return false;
    }

    // API validation
    validateSchoolWithAPI();
    return false; // Prevent immediate submission, let API validation handle it
}

function validateRegistrationNumber(registrationNumber) {
    const re = /^[A-Z]{2}\d{6}$/;
    return re.test(registrationNumber);
}

function validateUgandanPhone(phone) {
    const re = /^[\+]?256[\d]{9}$/;
    return re.test(phone.replace(/[\s\-\(\)]/g, ''));
}

function validateSchoolWithAPI() {
    const formData = new FormData(document.getElementById('schoolForm'));
    const schoolData = {};

    for (let [key, value] of formData.entries()) {
        if (value.trim() !== '') {
            schoolData[key] = value;
        }
    }

    // Convert boolean fields
    const booleanFields = ['hasLibrary', 'hasLaboratory', 'hasComputerLab', 'hasDormitories', 'hasPlayground', 'hasElectricity', 'hasWaterSupply'];
    booleanFields.forEach(field => {
        if (schoolData[field]) {
            schoolData[field] = schoolData[field] === 'on';
        }
    });

    showSuccess('Validating school data...');

    fetch('/api/schools/validate', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('jwtToken')
        },
        body: JSON.stringify(schoolData)
    })
    .then(response => {
        if (response.ok) {
            showSuccess('School data is valid! Submitting form...');
            // If validation passes, submit the form
            document.getElementById('schoolForm').submit();
        } else {
            return response.json().then(data => {
                throw new Error(data.message || 'Validation failed');
            });
        }
    })
    .catch(error => {
        console.error('Validation error:', error);
        showError('Validation failed: ' + error.message);
    });
}

// Add validation button to school form
function addValidationButton() {
    const form = document.getElementById('schoolForm');
    if (form) {
        const submitButton = form.querySelector('button[type="submit"]');
        if (submitButton) {
            const validateButton = document.createElement('button');
            validateButton.type = 'button';
            validateButton.className = 'btn btn-secondary';
            validateButton.textContent = 'Validate Data';
            validateButton.onclick = validateSchoolWithAPI;

            submitButton.parentNode.insertBefore(validateButton, submitButton);
        }
    }
}

// Initialize form validation on page load
document.addEventListener('DOMContentLoaded', function() {
    // Login form
    const loginForm = document.getElementById('loginForm');
    if (loginForm) {
        loginForm.addEventListener('submit', function(e) {
            if (!validateLoginForm()) {
                e.preventDefault();
            }
        });
    }

    // School form
    const schoolForm = document.getElementById('schoolForm');
    if (schoolForm) {
        // Add validation button
        addValidationButton();

        // Update form submission to use API validation
        schoolForm.addEventListener('submit', function(e) {
            e.preventDefault(); // Always prevent default submission
            if (validateSchoolForm()) {
                // This will call the API validation
            }
        });
    }

    // Check for flash messages from server
    const urlParams = new URLSearchParams(window.location.search);
    const error = urlParams.get('error');
    if (error) {
        showError(error);
    }

    // Check for success/error messages in flash attributes (Thymeleaf)
    const flashError = document.querySelector('[data-flash="error"]');
    if (flashError) {
        showError(flashError.textContent);
    }

    const flashSuccess = document.querySelector('[data-flash="success"]');
    if (flashSuccess) {
        showSuccess(flashSuccess.textContent);
    }
});