let main = {
    init : function ()  {
        let _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },
    save : () => {
        let data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType : 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : () => {
        let data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        let id = $("#id").val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(() => {
            alert("글이 수정되었습니다.");
            window.location.href = '/';
        }).fail(error => {
            alert(JSON.stringify(error));
        });
    },

};

main.init();