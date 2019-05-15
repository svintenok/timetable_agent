<#include "base.ftl">
<#macro title>Timetable Agent | Аудитории</#macro>

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
                    <a href="#" class="btn btn-outline-dark"><i class="fa fa-times"></i>Удалить аудиторию</a>
                </div>
            </div>
            <br>

            <form action="/data/auditories/${auditory.id}" method="POST">

                <p>Аудитория № ${auditory.number}</p>

                <table class="table">
                    <thead class="thead-light">
                    <tr>
                        <th scope="col">#</th>
                        <#list day_timeslots as day>
                            <th scope="col">${day.dayName}</th>
                        </#list>
                    </tr>
                    </thead>
                    <tbody>
                    <#list time_timeslots as time>
                    <tr>
                        <th scope="row">${time.interval}</th>
                        <#list day_timeslots as day>
                            <td>${auditory}</td>
                        </#list>
                    </tr>
                    </#list>
                    </tbody>
                </table>

                <button type="submit" class="btn btn-primary">Сохранить</button>
            </form>
        </div>
    </div>


</div>

</#macro>