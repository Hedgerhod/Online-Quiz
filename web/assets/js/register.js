// contructor
function Validator(options) {
    function getParentElement(element, selector) {
        while (element.parentElement) {
            if (element.parentElement.matches(selector)) {
                return element.parentElement;
            }
            element = element.parentElement;
        }
    }

    var selectorRules = {};

    // hàm thực hiện validate
    function validate(inputElement, rule) {
        var errorElement = getParentElement(inputElement, options.formGroupSelector).querySelector(options.querySelector);
        var errorMessage;

        // lấy ra các rule của selector
        var rules = selectorRules[rule.selector];

        // lặp qua từng rule và ktra
        for (var i = 0; i < rules.length; ++i) {
            switch (inputElement.type) {
                case "checkbox":
                case "radio":
                    errorMessage = rules[i](formElement.querySelector(rule.selector + ":checked"));
                    break;
                default:
                    errorMessage = rules[i](inputElement.value);
            }
            // nêu rule có lỗi dừng ktra
            if (errorMessage)
                break;
        }

        if (errorMessage) {
            errorElement.innerText = errorMessage;
            getParentElement(inputElement, options.formGroupSelector).classList.add('invalid');
        } else {
            errorElement.innerText = '';
            getParentElement(inputElement, options.formGroupSelector).classList.remove('invalid');
        }
        return !errorMessage;
    }

    // lấy element của form cần validate
    var formElement = document.querySelector(options.form);

    if (formElement) {
        formElement.onsubmit = function (e) {
            e.preventDefault();

            var isFormValid = true;

            // thực hiện qua từng rule và validate
            options.rules.forEach(function (rule) {

                var inputElement = formElement.querySelector(rule.selector);
                var isValide = validate(inputElement, rule);
                if (!isValide) {
                    isFormValid = false;
                }
            });

            if (isFormValid) {
                // trường hợp submit với javaScript
                if (typeof options.onSubmit === 'function') {
                    var enableInputs = formElement.querySelectorAll('[name]');

                    var formValues = Array.from(enableInputs).reduce(function (values, input) {

                        switch (input.type) {
                            case "checkbox":
                                if (!input.matches(":checked")) {
                                    values[input.name] = '';
                                    return values;
                                }
                                if (!Array.isArray(values[input.name])) {
                                    values[input.name] = [];
                                }

                                values[input.name].push(input.value);

                                break;
                            default:
                                (values[input.name] = input.value);
                        }

                        return values;
                    }, {});
                    options.onSubmit(formValues);
                } else {
                    // submit với hành vi mặc định của form
                    formElement.submit();
                }
            }
        };


        // xử lý lặp quá mỗi rule và xử lý (lắng nghe sự kiên blur)
        options.rules.forEach(function (rule) {

            // lưu lại các rules cho mỗi input
            if (Array.isArray(selectorRules[rule.selector])) {
                selectorRules[rule.selector].push(rule.test);
            } else {
                selectorRules[rule.selector] = [rule.test];
            }

            var inputElements = formElement.querySelectorAll(rule.selector);

            Array.from(inputElements).forEach(function (inputElement) {
                // Xử lý trường hợp blur khỏi input
                inputElement.onblur = function () {
                    validate(inputElement, rule);
                };
                // Xử lý mỗi khi người dùng nhập vào input
                inputElement.oninput = function () {
                    var errorElement = getParentElement(inputElement, options.formGroupSelector).querySelector(options.querySelector);
                    errorElement.innerText = '';
                    getParentElement(inputElement, options.formGroupSelector).classList.remove('invalid');
                };
            });
        });
    }
}



//định nghĩa rules
Validator.isRequired = function (selector, msg) {
    return {
        selector: selector,
        test: function (value) {
            return value ? undefined : msg ||
                    'Please enter this field!';
        }
    };
};
Validator.isDatePast= function (selector, msg) {
    return {
        selector: selector,
        test: function (value) {
            var currentDate = new Date();
            var inputDate = new Date(value);
            return inputDate < currentDate ? undefined : msg || 'Invalid birth date';
        }
    };
};
Validator.isEmail = function (selector, msg) {
    return {
        selector: selector,
        test: function (value) {
            var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            return regex.test(value) ? undefined : msg || 'Incorrect email format!';
        }
    };
};
Validator.isMinlength = function (selector, min, msg) {
    return {
        selector: selector,
        test: function (value) {
            return value.length >= min ? undefined : msg || `The password minimum length is ${min}`;
        }
    };
};
Validator.isPhoneNumber = function (selector, msg) {
    return {
        selector: selector,
        test: function (value) {
            // Kiểm tra nếu số điện thoại rỗng hoặc chỉ chứa khoảng trắng
            if (value.trim() === '') {
                return undefined; // Trả về undefined nếu số điện thoại được cho phép trống
            }

            // Kiểm tra số điện thoại theo một định dạng cụ thể (ví dụ: chỉ chấp nhận chữ số và dấu '+')
            var regex = /^[0-9+]+$/;
            if (!regex.test(value)) {
                return msg || 'Invalid phone number format!';
            }

            // Kiểm tra độ dài của số điện thoại
            if (value.length !== 11) {
                return msg || 'Phone number must be 11 digits!';
            }

            // Các kiểm tra khác cho số điện thoại (nếu cần)

            return undefined; // Trả về undefined nếu số điện thoại hợp lệ
        }
    };
};
Validator.isMaxlength = function (selector, max, msg) {
    return {
        selector: selector,
        test: function (value) {
            return value.length <= max ? undefined : msg || `The password maximum length is ${max}`;
        }
    };
};

Validator.isConfirmed = function (selector, isConfirmed, msg) {
    return {
        selector: selector,
        test: function (value) {
            return value === isConfirmed() ? undefined : msg || "Re-enter password is not matched!";
        }
    };
};


