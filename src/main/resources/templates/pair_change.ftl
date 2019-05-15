<#include "base.ftl">
<#macro title>Timetable Agent | Изменение пары</#macro>

<#macro content>

<div class="container-fluid">

    <div class="row mb-3">
        <div class="col-md-7 themed-grid-col">

            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h3 class="h4" id="headText">Изменение пары</h3>
                <div class="mb-2 mb-md-0">
                    <form action="/current_timetable/delete/${pair.id}" method="POST">
                        <button type="button" class="btn btn-outline-dark"><i class="fa fa-times"></i> Удалить пару</button>



                    </form>
                </div>
            </div>
            <br>

    <form action="/current_timetable/change/${pair.id}" method="POST">
        <p>Предмет: ${pair.subjectCourse.name}<#if pair.type.type="курс по выбору">: ${pair.optionalCourseSubject.name}</#if></p>
        <#if pair.group??>
            <p>Группа: ${pair.group.groupNum}</p>
        <#else>
            <p>Группы: ${pair.groupSet.toString()}</p>
        </#if>
        <p>Тип: ${pair.type.type}</p>
        <div class="form-group row">
            <label for="timeslot_day" class="col-sm-2 col-form-label">День</label>
            <div class="col-sm-10">
            <select name="timeslot_day" class="form-control" id="timeslot_day">
                <#list day_timeslots as day_timeslot>
                    <option value="${day_timeslot.dayNum}" <#if offerPair??><#if day_timeslot.dayNum==offerPair.timeslotDay.dayNum>selected</#if><#else><#if day_timeslot.dayNum==pair.timeslotDay.dayNum>selected</#if></#if>>${day_timeslot.dayName}</option>
                </#list>
            </select>
            </div>
        </div>

        <div class="form-group  row">
            <label for="timeslot_time" class="col-sm-2 col-form-label">Время</label>
            <div class="col-sm-10">
            <select name="timeslot_time" class="form-control" id="timeslot_time">
                <#list time_timeslots as time_timeslot>
                    <option value="${time_timeslot.pairNum}" <#if offerPair??><#if time_timeslot.pairNum==offerPair.timeslotTime.pairNum>selected</#if><#else><#if time_timeslot.pairNum==pair.timeslotTime.pairNum>selected</#if></#if>>${time_timeslot.interval}</option>
                </#list>
            </select>
            </div>
        </div>
        <!--
        <div class="form-group  row">
            <label for="timeslot_time" class="col-sm-2 col-form-label">Четность недели</label>
            <div class="col-sm-10">
                <select name="timeslot_time" class="form-control" id="timeslot_time">
                </select>
            </div>
        </div>
                -->

        <#if pair.type.type != "физкультура">

        <div class="form-group  row">
            <label for="professor" class="col-sm-2 col-form-label">Преподаватель</label>
            <div class="col-sm-10">
            <select name="professor" class="form-control" id="professor">
                    <#list professor_list as professor>
                        <option value="${professor.id}" <#if offerPair??><#if professor.id==offerPair.professor.id>selected</#if><#else><#if professor.id==pair.professor.id>selected</#if></#if>>${professor.name}</option>
                    </#list>
            </select>
            </div>
        </div>

        </#if>

        <div class="form-group  row">
            <label for="auditory" class="col-sm-2 col-form-label">Аудитория</label>
            <div class="col-sm-10">
            <select name="auditory" class="form-control" id="auditory">
                    <#list auditory_list as auditory>
                        <option value="${auditory.id}" <#if offerPair??><#if auditory.id==offerPair.auditory.id>selected</#if><#else><#if auditory.id==pair.auditory.id>selected</#if></#if>>${auditory.number}</option>
                    </#list>
            </select>
            </div>
        </div>

        <#if action??>
        <#if action="save">
            <div class="alert alert-success" role="alert">
                Изменения сохранены
            </div>
            <button type="submit" class="btn btn-primary">Проверить изменение</button>
        <#elseif action="cancel">
            <div class="alert alert-success" role="alert">
                Изменения отменены
            </div>
            <button type="submit" class="btn btn-primary">Проверить изменение</button>
        </#if>
        <#else>
        <button type="submit" class="btn btn-primary">Проверить изменение</button>
        </#if>
    </form>
    <#if action?? && action="offer">
        <#if hardCount==0 && softCount==0>
            <div class="alert alert-success" role="alert">
                Все ограничения выполняются
            </div>
        <#else>
            <div class="alert alert-danger" role="alert">
                Нарушений жестких ограничений: ${hardCount}<#if hardCountChange!=0>(<#if (hardCountChange>0)>+</#if>${hardCountChange})</#if>, мягких ограничений: ${softCount}<#if softCountChange!=0>(<#if (softCountChange>0)>+</#if>${softCountChange})</#if>
            </div>
        </#if>
        <div class="btn-group" role="group">
            <form action="/current_timetable/change/${pair.id}/save" method="POST">
                <button type="submit" class="btn btn-primary">Сохранить</button>
            </form>

            <form action="/current_timetable/change/${pair.id}/cancel" method="POST">
                <button type="submit" class="btn btn-primary">Отменить</button>
            </form>
        </div>
    </#if>

        </div>

        <div class="col-md-5 themed-grid-col">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h3 class="h4" id="headText">Нарушения
                <#if (hardCount>0)><span class="badge badge-pill badge-danger">Жесткие: ${hardCount}</span></#if>
                <#if (softCount>0)><span class="badge badge-pill badge-warning">Мягкие: ${softCount}</span></#if>
                </h3>
                <div class="mb-2 mb-md-0">
                    <a href="/restrictions" class="btn btn-outline-dark"><i class="fa fa-cog"></i>Настроить ограничения</a></div>
            </div>
            <br>
            <#if violations?has_content>
            <table class="table">
                <tbody>
                    <#list violations as violation>
                    <tr <#if violation.hard>class="table-danger"<#else>class="table-warning"</#if>>
                        <td>${violation.warningString()}</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
            <#else>
                <p>Все ограничения выполняются</p>
            </#if>
        </div>
    </div>
</div>

</#macro>