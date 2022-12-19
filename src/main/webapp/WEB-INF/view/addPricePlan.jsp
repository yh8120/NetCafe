<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<title>プラン管理</title>
</head>
<body>
    <div class="container">
        <h1>プラン登録</h1>
        <div class="row">
            <div class="col">
                <form action="" method="post">

                    <c:if test="${not empty planNameError }">
                        <div class="error-message">
                            <c:out value="${planNameError}" />
                        </div>
                    </c:if>
                    <div class="row mb-3 align-items-center">
                        <div class="col-auto">
                            <label for="formPlanName">プラン名</label>
                        </div>
                        <div class="col-auto">
                            <input type="text" name="planName" id="formPlanName" class="form-control"
                                value=<c:out value="${planName }"/>>
                        </div>
                    </div>

                    <div class="row mb-3 align-items-center">
                        <c:if test="${not empty planStartError }">
                            <div class="error-message">
                                <c:out value="${planStartError}"></c:out>
                            </div>
                        </c:if>

                        <div class="col-auto">
                            <label for="formStartTime">開始日時</label>
                        </div>
                        <div class="col-auto">
                            <input type="date" name="planStart" id="formPlanStart" class="form-control"
                                value=<c:out value="${planStart }"/>>
                        </div>
                        <c:if test="${not empty planEndError }">
                            <div class="error-message">
                                <c:out value="${planEndError}"></c:out>
                            </div>
                        </c:if>

                        <div class="col-auto">
                            <label for="formPlanEnd">終了日時</label>
                        </div>
                        <div class="col-auto">
                            <input type="date" name="planEnd" id="formPlanEnd" class="form-control"
                                value=<c:out value="${planEnd }"/>>
                        </div>
                    </div>

                    <div class="row mb-3 align-items-center">
                        <c:if test="${not empty startTimeError }">
                            <div class="error-message">
                                <c:out value="${startTimeError}"></c:out>
                            </div>
                        </c:if>
                        <div class="col-auto">
                            <label for="formStartTime">開始時間</label>
                        </div>
                        <div class="col-4">
                            <div class="input-group">
                                <input type="number" class="form-control" name="startTimeHour" id="formStartTimeHour"
                                    placeholder="0-47" aria-label="時">
                                <span class="input-group-text">時</span>
                                <input type="number" class="form-control" name="startTimeHour" id="formStartTimeMinute"
                                    placeholder="0-59" aria-label="分">
                                <span class="input-group-text">分</span>
                            </div>
                        </div>
                        <c:if test="${not empty endTimeError }">
                            <div class="error-message">
                                <c:out value="${endTimeError}"></c:out>
                            </div>
                        </c:if>
                        <div class="col-auto">
                            <label for="formEndTime">終了時間</label>
                        </div>
                        <div class="col-4">
                            <div class="input-group">
                                <input type="number" class="form-control" name="endTimeHour" id="formEndTimeHour"
                                    placeholder="0-47" aria-label="時" min="0" max="47">
                                <span class="input-group-text">時</span>
                                <input type="number" class="form-control" name="endTimeHour" id="formEndTimeMinute"
                                    placeholder="0-59" aria-label="分" min="0" max="59">
                                <span class="input-group-text">分</span>
                            </div>
                        </div>
                    </div>

                    <div class="row mb-3 align-items-center">
                        <c:if test="${not empty basicPriceError }">
                            <div class="error-message">
                                <c:out value="${basicPriceError}"></c:out>
                            </div>
                        </c:if>
                        <div class="col-auto">
                            <label for="formBasicPrice">基本料金</label>
                        </div>
                        <div class="col-auto">
                            <div class="input-group">
                                <input type="number" class="form-control" name="basicPrice" id="formBasicPrice"
                                    placeholder="" aria-label="基本料金" min="0" max="999999">
                                <span class="input-group-text">円</span>
                            </div>
                        </div>

                        <c:if test="${not empty basicTimeError }">
                            <div class="error-message">
                                <c:out value="${basicTimeError}"></c:out>
                            </div>
                        </c:if>
                        <div class="col-auto">
                            <label for="formBasicTime">基本時間</label>
                        </div>
                        <div class="col-auto">
                            <div class="input-group">
                                <input type="number" class="form-control" name="basicTimeHour" id="formBasicTimeHour"
                                    placeholder="" aria-label="時" min="0" max="999999">
                                <span class="input-group-text">時</span>
                                <input type="number" class="form-control" name="basicTimeMinute"
                                    id="formBasicTimeMinute" placeholder="" aria-label="分" min="0" max="59">
                                <span class="input-group-text">分</span>
                            </div>
                        </div>
                    </div>

                    <div class="row mb-3 align-items-center">
                        <c:if test="${not empty addPriceError }">
                            <div class="error-message">
                                <c:out value="${addPriceError}"></c:out>
                            </div>
                        </c:if>
                        <div class="col-auto">
                            <label for="formAddPrice">追加料金</label>
                        </div>
                        <div class="col-auto">
                            <div class="input-group">
                                <input type="number" class="form-control" name="addPrice" id="formAddPrice"
                                    placeholder="" aria-label="基本料金" min="0" max="999999">
                                <span class="input-group-text">円</span>
                            </div>
                        </div>

                        <c:if test="${not empty addTimeError }">
                            <div class="error-message">
                                <c:out value="${addTimeError}"></c:out>
                            </div>
                        </c:if>
                        <div class="col-auto">
                            <label for="formAddTime">追加時間</label>
                        </div>
                        <div class="col-auto">
                            <div class="input-group">
                                <input type="number" class="form-control" name="addTimeHour" id="formAddTimeHour"
                                    placeholder="" aria-label="時" min="0" max="999999">
                                <span class="input-group-text">時</span>
                                <input type="number" class="form-control" name="addTimeMinute" id="formAddTimeMinute"
                                    placeholder="" aria-label="分" min="0" max="59">
                                <span class="input-group-text">分</span>
                            </div>
                        </div>
                    </div>

                    <div class="mb-3 form-check form-switch">
                        <input class="form-check-input" name="scopeSetting" type="checkbox" id="formScopeSetting">
                        <label class="form-check-label" for="formScopeSetting">スコープ設定</label>
                    </div>

                    <fieldset disabled>
                        <div class="row ms-1">
                            <div id="scopeSettingItems" class="col">

                                <div class="mb-3">
                                    <label for="formScopeRoomType">部屋タイプ</label>
                                    <select name="scopeRoomType" id="formScopeRoomType" class="form-control">
                                        <option value="0">未設定</option>
                                        <c:forEach items="${roomTypeList}" var="roomType">
                                            <option value="<c:out value="${roomType.roomTypeId}" />"
                                                <c:if test="${roomType.roomTypeId == roomTypeId}">selected</c:if>>
                                                <c:out value="${roomType.roomTypeName}" />
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="mb-3">
                                    <label for="formScopeSex">性別</label>
                                    <select name="scopeSex" id="formScopeSex" class="form-control">
                                        <option value="0">未設定</option>
                                        <c:forEach items="${sexList}" var="sex">
                                            <option value="<c:out value="${sex.sexId}" />"
                                                <c:if test="${sex.sexId == sexId}">selected</c:if>>
                                                <c:out value="${sex.sexName}" />
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="mb-3">
                                    <label for="formScopeCustomerClass">会員クラス</label>
                                    <select name="scopeCustomerClass" id="formScopeCustomerClass" class="form-control">
                                        <option value="0">未設定</option>
                                        <c:forEach items="${customerClassList}" var="customerClass">
                                            <option value="<c:out value="${customerClass.customerClassId}" />"
                                                <c:if test="${customerClass.customerClassId == customerClassId}">selected</c:if>>
                                                <c:out value="${customerClass.customerClassName}" />
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>


                                <div class="mb-3">
                                    <input type="checkbox" name="forMonday" class="form-check-input" id="formForMonDay">
                                    <label class="form-check-label" for="formForSunday">月</label>
                                    <input type="checkbox" name="forTuesday" class="form-check-input"
                                        id="formForTuesday">
                                    <label class="form-check-label" for="formForSunday">火</label>
                                    <input type="checkbox" name="forWednesday" class="form-check-input"
                                        id="formForWednesday">
                                    <label class="form-check-label" for="formForSunday">水</label>
                                    <input type="checkbox" name="forThursday" class="form-check-input"
                                        id="formForThursday">
                                    <label class="form-check-label" for="formForSunday">木</label>
                                    <input type="checkbox" name="forFriday" class="form-check-input" id="formForFriday">
                                    <label class="form-check-label" for="formForSunday">金</label>
                                    <input type="checkbox" name="forSaturday" class="form-check-input"
                                        id="formForSaturday">
                                    <label class="form-check-label" for="formForSunday">土</label>
                                    <input type="checkbox" name="forSunday" class="form-check-input" id="formForSunday">
                                    <label class="form-check-label" for="formForSunday">日</label>
                                    <input type="checkbox" name="forHolidays" class="form-check-input"
                                        id="formForHlidays">
                                    <label class="form-check-label" for="formForSunday">祝日</label>
                                </div>
                            </div>
                        </div>

                    </fieldset>

                    <div class="mb-3">
                        <input type="submit" class="btn btn-success" value="登録">
                        <a href="listPricePlan" class="btn btn-light">キャンセル</a>
                    </div>

                </form>
            </div>
        </div>
    </div>
    <script src="js/jquery-3.6.1.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
