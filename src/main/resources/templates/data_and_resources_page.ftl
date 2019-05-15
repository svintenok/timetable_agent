<#include "base.ftl">
<#macro title>Timetable Agent | Данные и ресурсы</#macro>

<#macro content>

<div class="container-fluid">

    <div class="row mb-3">
        <div class="col-md-3 themed-grid-col">
            <ul class="nav flex-column nav-pills">
                <li class="nav-item">
                    <a class="nav-link active" href="/data/auditories">Аудитории</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Преподаватели</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Предметы</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Курсы и группы</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Наборы групп</a>
                </li>
            </ul>
        </div>

        <div class="col-md-9 themed-grid-col">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h3 class="h4" id="headText">Аудитории</h3>
                <div class="mb-2 mb-md-0">
                    <a href="#" class="btn btn-outline-dark"><i class="fa fa-plus"></i> Добавить аудиторию</a>
                </div>
            </div>
            <br>

            <div class="list-group">
                <#list auditory_list as auditory>
                    <a href="/data/auditories/${auditory.id}" class="list-group-item list-group-item-action"><i class="fa fa-bars"></i>
                        Аудитория № ${auditory.number}
                    </a>
                </#list>
            </div>
        </div>
    </div>


</div>

</#macro>