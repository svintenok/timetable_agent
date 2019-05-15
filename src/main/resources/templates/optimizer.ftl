<#include "base.ftl">
<#macro title>Timetable Agent | Оптимизатор</#macro>

<#macro content>

<div class="container-fluid">
<!---
<div class="row justify-content-center">
<div class="col col-md-6">
-->
    <div class="row mb-3">
        <div class="col-md-6 themed-grid-col">

            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h3 class="h4" id="headText">Оптимизация расписания</h3>
            </div>
            <br>

            <p>Нарушения жестких ограничений: ${hardCount}</p>
            <p>Нарушения мягких ограничений: ${softCount}</p>

            <div id="loading_alert" class="alert alert-primary" role="alert" style="display: none">
                <div class="d-flex align-items-center">
                    <strong> Оптимизация выполняется...</strong>
                    <div class="spinner-border ml-auto" role="status" aria-hidden="true"></div>
                </div>
            </div>

            <br>

            <button id="optimize" type="button" class="btn btn-primary">
                Оптимизировать расписание
            </button>

        </div>

        <div class="col-md-6 themed-grid-col">


            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h3 class="h4" id="headText">Конфигурация ограничений</h3>
                <div class="mb-2 mb-md-0">
                    <a href="/restrictions" class="btn btn-outline-dark"><i class="fa fa-cog"></i>Изменить ограничения</a>
                </div>
            </div>
            <br/>

            <form action="/optimizer/configure" method="POST">

                <div class="form-group">
                <#list restrictions as restriction>

                    <div class="form-group">
                        <div class="custom-control custom-switch">
                            <input name="restriction_check" value="${restriction.id}" type="checkbox" class="custom-control-input" id="switch${restriction.id}" <#if restriction.enabled>checked</#if>>
                            <label class="custom-control-label" for="switch${restriction.id}"> ${restriction.name}</label>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <input name="hardCheck" value="${restriction.id}" class="form-check-input" type="checkbox" id="hardCheck${restriction.id}" <#if restriction.hard>checked</#if>>
                            <label class="form-check-label" for="hardCheck${restriction.id}">
                                Жесткое
                            </label>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="inputPriority">Приоритет</label>
                            <select name="priority${restriction.id}" id="inputPriority" class="form-control">
                                <#list [1, 2, 3, 4, 5] as priority>
                                <option value="${priority}" <#if restriction.priority==priority>selected</#if>>${priority}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <br>
                </#list>

                <button type="submit" class="btn btn-primary">Применить</button>
                </div>
            </form>

        </div>
    </div>
</div>

</#macro>


<#macro scripts>
<script>

    document.getElementById("optimize").addEventListener("click", function () {

        $("#loading_alert").show();

        $.ajax({
            data: {},
            type: "POST",
            url: "/optimizer/optimize",
            async: true,
            success: function (result) {
                var link = "/optimizer/result";
                $(location).attr('href', link);
            }
        });
    });


</script>
</#macro>