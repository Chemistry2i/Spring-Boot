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

// School form validation
function validateSchoolForm() {
    const name = document.getElementById('name').value.trim();
    const address = document.getElementById('address').value.trim();
    const email = document.getElementById('email').value.trim();
    const phone = document.getElementById('phone').value.trim();
    const website = document.getElementById('website').value.trim();

    if (!name) {
        showError('School name is required');
        return false;
    }

    if (!address) {
        showError('Address is required');
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

    if (!validatePhone(phone)) {
        showError('Please enter a valid phone number');
        return false;
    }

    if (website && !validateUrl(website)) {
        showError('Please enter a valid website URL');
        return false;
    }

    return true;
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
        schoolForm.addEventListener('submit', function(e) {
            if (!validateSchoolForm()) {
                e.preventDefault();
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