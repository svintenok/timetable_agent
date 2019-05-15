<#include "base.ftl">
<#macro title>Timetable Agent | Факторы и ограничения</#macro>

<#macro content>

<div class="container-fluid">

    <div class="row mb-3">
        <div class="col-md-7 themed-grid-col">

            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h3 class="h4" id="headText">Ограничения</h3>
                <div class="mb-2 mb-md-0">
                    <a href="#" class="btn btn-outline-dark"><i class="fa fa-plus"></i> Добавить ограничение</a>
                </div>
            </div>
            <br>

            <div class="list-group">
            <#list restrictions as restriction>
                <a href="#" class="list-group-item list-group-item-action">
                    <div class="d-flex w-100 justify-content-between">
                        <h5 class="mb-1">${restriction.name}</h5>
                    </div>
                    <br>
                    <p>Фактор: ${restriction.factor.name}</p>
                    <p>Ограничение: "${restriction.operation.operation}${restriction.restrictionValue}"</p>
                </a>
            </#list>
            </div>
        </div>

        <div class="col-md-5 themed-grid-col">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h3 class="h4" id="headText">Факторы</h3>
                <div class="mb-2 mb-md-0">
                    <a href="#" class="btn btn-outline-dark"><i class="fa fa-plus"></i> Добавить фактор</a>
                </div>
            </div>
            <br>

            <div class="list-group">
            <#list factors as factor>
                <a href="#" class="list-group-item list-group-item-action">
                    ${factor.name}
                </a>
            </#list>
            </div>
        </div>
    </div>

</div>

</#macro>
