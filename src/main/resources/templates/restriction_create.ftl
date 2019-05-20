<#include "base.ftl">
<#macro title>Timetable Agent | Ограничения</#macro>

<#macro content>

<div class="container-fluid">

    <div class="row justify-content-center">
        <div class="col col-md-6 justify-content-center">

            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h3 class="h4" id="headText">Добавление ограничения</h3>
            </div>
            <br>


            <form action="/restrictions/add" method="POST">

                <div class="form-group row">
                    <label for="restriction_name" class="col-sm-2 col-form-label">Название</label>
                    <div class="col-sm-10">
                        <input name="restriction_name" type="text" class="form-control" id="restriction_name" placeholder="Название ограничения">
                    </div>
                </div>

                <div class="form-group row">
                    <label for="restriction_factor" class="col-sm-2 col-form-label">Фактор</label>
                    <div class="col-sm-10">
                        <select name="restriction_factor" class="form-control" id="restriction_factor">
                        <#list factors as factor>
                            <option value="${factor.id}">${factor.name}</option>
                        </#list>
                        </select>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="operation" class="col-sm-2 col-form-label">Операция</label>
                    <div class="col-sm-10">
                        <select name="restriction_operation" class="form-control" id="operation">
                        <#list operations as operation>
                            <option value="${operation.id}">${operation.operation}</option>
                        </#list>
                        </select>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="operation" class="col-sm-2 col-form-label">Значение ограничения</label>
                    <div class="col-sm-10">
                        <input name="restriction_value" type="text" class="form-control" id="restriction_value" placeholder="3">
                    </div>
                </div>

                <div class="form-group form-check">
                    <input name="isHard" value="isHard" class="form-check-input" type="checkbox" id="isHard">
                    <label for="isHard" class="form-check-label">Жесткое</label>
                </div>

                <div class="form-group row">
                    <label for="inputPriority" class="col-sm-2 col-form-label">Приоритет</label>
                    <div class="col-sm-10">
                        <select name="priority" id="inputPriority" class="form-control">
                        <#list [1, 2, 3, 4, 5] as priority>
                            <option value="${priority}" <#if priority==5>selected</#if>>${priority}</option>
                        </#list>
                        </select>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary">Создать ограничение</button>

            </form>
        </div>
    </div>

</div>

</#macro>