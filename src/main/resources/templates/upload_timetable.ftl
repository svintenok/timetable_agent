<#include "base.ftl">
<#macro title>Timetable Agent | Загрузка расписания</#macro>

<#macro content>

<div class="container-fluid">

    <div class="row justify-content-center">
    <div class="col col-md-6 justify-content-center">


        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
            <h3 class="h4" id="headText">Загрузка расписания</h3>
        </div>
        <br>

        <form action="/upload_timetable" enctype="multipart/form-data" method="POST">
            <div class="form-group">
                <label for="exampleFormControlFile1">Данные расписания в JSON</label>
                <input type="file" name="timetable_data" class="form-control-file" id="exampleFormControlFile1">
            </div>

            <div id="loading_alert" class="alert alert-primary" role="alert" style="display: none">
                <div class="d-flex align-items-center">
                    <strong> Импортирование данных...</strong>
                    <div class="spinner-border ml-auto" role="status" aria-hidden="true"></div>
                </div>
            </div>

            <button type="submit" id="timetable_import" class="btn btn-primary">Импорт данных</button>
        </form>

    </div>
    </div>
</div>

</#macro>

<#macro scripts>
<script>
    document.getElementById("timetable_import").addEventListener("click", function () {

        $("#loading_alert").show();
    });
</script>
</#macro>