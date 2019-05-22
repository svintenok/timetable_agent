<#include "base.ftl">
<#macro title>Timetable Agent | Результат оптимизации</#macro>

<#macro content>

<div class="container-fluid">


    <div class="row mb-3">

        <div class="col-md-5 themed-grid-col">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h3 class="h4" id="headText">Результат оптимизации</h3>
            </div>
            <br>

            <p>Нарушения жестких ограничений: ${hardCount}<#if hardCountChange!=0>(<#if (hardCountChange>0)>+</#if>${hardCountChange})</#if></p>
            <p>Нарушения мягких ограничений: ${softCount}<#if softCountChange!=0>(<#if (softCountChange>0)>+</#if>${softCountChange})</#if></p>
            <p>Измененных пар: ${reassigned_pairs?size}</p>


            <h5 class="h4" id="headText">Нарушения до оптимизации:</h5>
            <#if old_violations?has_content>
            <table class="table">
                <tbody>
                    <#list old_violations as violation>
                    <tr <#if violation.hard>class="table-danger"<#else>class="table-warning"</#if>>
                        <td>${violation.warningString()}</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
            <#else>
                <p>Нет нарушений ограничений</p>
            </#if>
            <h5 class="h4" id="headText">Нарушения после оптимизации:</h5>
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
                <p>Нет нарушений ограничений</p>
            </#if>
        </div>

        <div class="col-md-7 themed-grid-col">

            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h3 class="h4" id="headText">Измененные пары</h3>
            </div>
            <br>

            <#list reassigned_pairs as pair>
            <div class="list-group">
                <a href="/current_timetable/change/${pair.id}/" class="list-group-item list-group-item-action">
                    <div class="d-flex w-100 justify-content-between">
                        <#if pair.timeslot.id != pair.assignedPairOffer.timeslot.id>
                        <p class="mb-1 text-danger">${pair.timeslotDay.dayName}, ${pair.timeslotTime.interval} -> ${pair.assignedPairOffer.timeslotDay.dayName}, ${pair.assignedPairOffer.timeslotTime.interval}</p>
                        <#else>
                        <p class="mb-1">${pair.timeslotDay.dayName}, ${pair.timeslotTime.interval}</p>
                        </#if>
                        <small>${pair.type.type}</small>
                    </div>
                    <h5 class="mb-1">${pair.subjectCourse.name}<#if pair.type.type="курс по выбору">: ${pair.optionalCourseSubject.name}</#if></h5>
                    <br>
                    <div class="container borber">
                        <#if pair.group??>
                            <p>Группа: ${pair.group.groupNum}</p>
                                                <#else>
                            <p>Группы: ${pair.groupSet.toString()}</p>
                        </#if>
                        <#if pair.type.type != "физкультура">
                            <p>Профессор: ${pair.professor.name}</p>
                        </#if>
                        <#if pair.auditory.id != pair.assignedPairOffer.auditory.id>
                        <p>Aудитория: <p class="text-danger">${pair.auditory.number} -> ${pair.assignedPairOffer.auditory.number}</p>
                        <#else>
                        <p>Aудитория: ${pair.auditory.number}</p>
                        </#if>
                    </div>
                </a>
            </div>
            </#list>

            <div class="btn-group" role="group">
                <form action="/optimizer/save_results" method="POST">
                    <button type="submit" class="btn btn-success">Применить изменения</button>
                </form>

                <form action="/optimizer/cancel_results" method="POST">
                    <button type="submit" class="btn btn-danger">Отменить</button>
                </form>
            </div>
        </div>
    </div>

</div>

</#macro>