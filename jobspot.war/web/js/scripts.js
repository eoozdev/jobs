$(document).ready(function () {
    
    $('.login-link').on('click', function (evt) {
        console.log(evt);
        evt.preventDefault();
        $(this).parent().find('.login-modal').fadeToggle();
    });
    
    $('.login-btn').on('click', function (evt) {
        evt.preventDefault();
        
        $('.email-error-message').text('');
        $('.password-error-message').text('');  
        $('.error-message span').text("");
        
        var loginEmailValue = $('#login-email').val(),
            loginPassword = $('#login-password').val(),
            emailPtrn = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i,
            validationObj = {
                state: true,
                errors: []
            };
        
        
        if (loginEmailValue !== '' && emailPtrn.test(loginEmailValue) &&  loginPassword !== '' && loginPassword.length >= 4) {
            validationObj.state = true;
            
            $.ajax({
                url: "/do.login",type: 'post', // performing a POST request
                data : {
                    username: loginEmailValue,
                    password: loginPassword
                },
                dataType: 'json',   
            }).done(function(data) {
                if ( data.REDIRECT_TO) {
                    window.location.href = data.REDIRECT_TO
                } else {
                    $('.error-message span').text("Check your username / password combination");
                }
            });
            
        } else {
            validationObj.state = false;
            validationObj.errors.push('Invalid Emial address');
            $('.email-error-message').text('Invalid email format');
            $('.password-error-message').text('Invalid password');
        }
        
    });
    
  
    $.ajax({
        url: "/do.public",type: 'GET', 
        data : {
            cc: 'EZGT'
        },
        dataType: 'json',   
    }).done(function(data) {
        data.map(function (option) {
            $('.location-menu').append('<option value="'+option.code+'">'+option.name+'</option>');
        })
    	
    });
 
    
    $.ajax({
        url: "/do.public",type: 'GET', 
        data : {
            cc: 'EZGJC'
        },
        dataType: 'json',   
    }).done(function(data) {
    	data.map(function (option) {
            $('.category-menu').append('<option value="'+option.code+'">'+option.name+'</option>');
        });
    });
    
    
    $('.search-btn-wrapper > a').on('click', function (e) {
        e.preventDefault();
        
        var locationValue = $('.location-menu').val(),
            categoryValue = $('.category-menu').val(),
            keyword = $('.search-keyword').val();
        
       $.ajax({
            url: "/do.public",type: 'GET', 
            data : {
                cc: 'EZPS',
            	town: locationValue,
                field: categoryValue,
                keyword: keyword
            },
            dataType: 'json',   
        }).done(function(data) {
            window.location.href = data.REDIRECT_TO
        }); 

    });
    
});