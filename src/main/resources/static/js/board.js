let index = {
    init: function () {
        $("#btn-save").on("click",()=>{ //function(){}, ()=>{} this를 바인딩하기 위해서!
            this.save();
        } )
        $("#btn-delete").on("click",()=>{
            this.deleteById();
        } )
        $("#btn-update").on("click",()=>{
            this.update();
        } )
        // $("#btn-login").on("click",()=>{ //function(){}, ()=>{} this를 바인딩하기 위해서!
        //     this.login();
        // } );
    },

    save:function () {
        // alert("user의 save함수 호출됨.");
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        };
        $.ajax({
            type:"POST",
            url:"/api/board" ,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (resp){
            alert("글쓰기가 완료되었습니다.");
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },
    deleteById:function () {
        let id = $("#id").text();

        $.ajax({
            type:"DELETE",
            url:"/api/board/"+id ,
            dataType: "json"
        }).done(function (resp){
            alert("삭제가 완료되었습니다.");
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },
    update:function () {
        let id = $("#id").val();

        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        };

        $.ajax({
            type:"PUT",
            url:"/api/board/"+id ,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (resp){
            alert("글수정이 완료되었습니다.");
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
}

index.init();

