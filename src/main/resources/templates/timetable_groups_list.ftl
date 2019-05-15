<#include "base.ftl">
<#macro title>Timetable Agent | Группы</#macro>

<#macro content>


<div class="container-fluid">

    <div class="row mb-3">
        <div class="col-md-7 themed-grid-col">

            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h3 class="h4" id="headText">Группы</h3>
                <div class="mb-2 mb-md-0">
                    <a href="#" class="btn btn-outline-dark"><i class="fa fa-plus"></i> Добавить пару</a>
                </div>
            </div>
            <br>

            <div class="list-group">
            <#list groups as group>
                <a href="/current_timetable/${group.groupNum}/" class="list-group-item list-group-item-action">
                    ${group.groupNum}, курс ${group.studyCourse.courseNum}
                </a>
            </#list>
            </div>
        </div>

        <div class="col-md-5 themed-grid-col">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h3 class="h4" id="headText">Нарушения
                <#if (hardCount>0)><span class="badge badge-pill badge-danger">Жесткие: ${hardCount}</span></#if>
                <#if (softCount>0)><span class="badge badge-pill badge-warning">Мягкие: ${softCount}</span></#if>
                </h3>
                <div class="mb-2 mb-md-0">
                    <a href="/restrictions" class="btn btn-outline-dark"><i class="fa fa-cog"></i>Настроить ограничения</a>
                </div>
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