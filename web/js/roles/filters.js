'use strict';

roles.filter('paging', function () {
    return function (input, start) {
        if (input) {
            return input.slice(start);
        }
    };
})
    .filter('bookname', function () {
        return function (input, bookFilterdInput) {
            if (input) {
                var result = new Array();
                for (var i = 0; i < input.length; i++) {
                    if (input[i].name.toLowerCase().indexOf(bookFilterdInput.toLowerCase()) != -1) {
                        result.push(input[i]);
                    }
                }
                return result;
            }
        };
    })
;