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
                <form action="" method="post" onsubmit="return false">

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
                                placeholder="XXX時間パック" value=<c:out value="${planName }"/>>
                        </div>
                    </div>

                    <div class="row mb-3 align-items-center">
                        <c:if test="${not empty planStartError }">
                            <div class="error-message">
                                <c:out value="${planStartError}"></c:out>
                            </div>

                        </c:if>

                        <div class="col-auto">
                            <label for="formStartTime">適用期間</label>
                        </div>
                        <div class="col-5">
                            <div class="input-group">
                                <input type="date" name="planStart" id="formPlanStart" class="form-control"
                                    value=<c:out value="${planStart }"/>>
                                <span class="input-group-text">～</span>
                                <input type="date" name="planEnd" id="formPlanEnd" class="form-control"
                                    value=<c:out value="${planEnd }"/>>
                            </div>
                        </div>
                    </div>

                    <div class="row mb-3 align-items-center">
                        <c:if test="${not empty startTimeError }">
                            <div class="error-message">
                                <c:out value="${startTimeError}"></c:out>
                            </div>
                        </c:if>
                        <c:if test="${not empty endTimeError }">
                            <div class="error-message">
                                <c:out value="${endTimeError}"></c:out>
                            </div>
                        </c:if>
                        <div class="col-auto">
                            <label for="formStartTime">入室時間</label>
                        </div>
                        <div class="col-3">
                            <div class="input-group">
                                <input type="number" class="form-control" name="startTimeHour" id="formStartTimeHour"
                                    placeholder="23" aria-label="時" min="0" max="23"
                                    value=<c:out value="${startTimeHour }"/>>
                                <span class="input-group-text">：</span>
                                <input type="number" class="form-control" name="startTimeMinute"
                                    id="formStartTimeMinute" placeholder="59" aria-label="分" min="0" max="59"
                                    value=<c:out value="${startTimeMinute }"/>>
                            </div>
                        </div>

                        <div class="col-auto">
                            <label for="formEndTime">～</label>
                        </div>
                        <div class="col-3">
                            <div class="input-group">
                                <input type="number" class="form-control" name="endTimeHour" id="formEndTimeHour"
                                    placeholder="47" aria-label="時" min="0" max="47"
                                    value=<c:out value="${endTimeHour }"/>>
                                <span class="input-group-text">：</span>
                                <input type="number" class="form-control" name="endTimeMinute" id="formEndTimeMinute"
                                    placeholder="59" aria-label="分" min="0" max="59"
                                    value=<c:out value="${endTimeMinute }"/>>
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
                                    placeholder="100" aria-label="基本料金" min="0" max="999999"
                                    value=<c:out value="${basicPrice }"/>>
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
                                    placeholder="00" aria-label="時" min="0" max="999999"
                                    value=<c:out value="${basicTimeHour}"/>>
                                <span class="input-group-text">時間</span>
                                <input type="number" class="form-control" name="basicTimeMinute"
                                    id="formBasicTimeMinute" placeholder="30" aria-label="分" min="0" max="59"
                                    value=<c:out value="${basicTimeMinute}"/>>
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
                                    placeholder="100" aria-label="基本料金" min="0" max="999999"
                                    value=<c:out value="${addPrice}"/>>
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
                                    placeholder="00" aria-label="時" min="0" max="999999"
                                    value=<c:out value="${addTimeHour}"/>>
                                <span class="input-group-text">時間</span>
                                <input type="number" class="form-control" name="addTimeMinute" id="formAddTimeMinute"
                                    placeholder="10" aria-label="分" min="0" max="59"
                                    value=<c:out value="${addTimeMinute}"/>>
                                <span class="input-group-text">分</span>
                            </div>
                        </div>
                    </div>

                    <div class="mb-3 form-check form-switch">
                        <input class="form-check-input" name="scopeSetting" type="checkbox" id="formScopeSetting"
                            <c:if test="${not empty scopeSetting}">checked</c:if>>
                        <label class="form-check-label" for="formScopeSetting">スコープ設定</label>
                    </div>

                    <fieldset id="fieldOfScopeSettingItems" disabled>
                        <div class="row ms-1">
                            <div id="scopeSettingItems" class="col">

                                <div class="row mb-3 align-items-center">
                                    <div class="col-auto">
                                        <label for="formScopeRoomType">部屋タイプ</label>
                                    </div>
                                    <div class="col-5">
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
                                </div>

                                <div class="row mb-3 align-items-center">
                                    <div class="col-auto">
                                        <label for="formScopeSex">性別タイプ</label>
                                    </div>
                                    <div class="col-5">
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
                                </div>

                                <div class="row mb-3 align-items-center">
                                    <div class="col-auto">
                                        <label for="formScopeCustomerClass">会員クラス</label>
                                    </div>
                                    <div class="col-5">
                                        <select name="scopeCustomerClass" id="formScopeCustomerClass"
                                            class="form-control">
                                            <option value="0">未設定</option>
                                            <c:forEach items="${customerClassList}" var="customerClass">
                                                <option value="<c:out value="${customerClass.customerClassId}" />"
                                                    <c:if test="${customerClass.customerClassId == customerClassId}">selected</c:if>>
                                                    <c:out value="${customerClass.customerClassName}" />
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="row mb-3 align-items-center">
                                    <c:if test="${not empty minAgeError }">
                                        <div class="error-message">
                                            <c:out value="${minAgeError}"></c:out>
                                        </div>

                                    </c:if>

                                    <div class="col-auto">
                                        <label for="formStartTime">適用年齢 </label>
                                    </div>
                                    <div class="col-3">
                                        <div class="input-group">
                                            <input type="number" class="form-control" name="minAge" id="formMinAge"
                                                placeholder="0" aria-label="最低年齢" min="0" max="200">
                                            <span class="input-group-text">才</span>
                                        </div>
                                    </div>

                                    <div class="col-auto">
                                        <label for="formEndTime">～</label>
                                    </div>
                                    <div class="col-3">
                                        <div class="input-group">
                                            <input type="number" class="form-control" name="maxAge" id="formMaxAge"
                                                placeholder="200" aria-label="最高年齢" min="0" max="200">
                                            <span class="input-group-text">才</span>
                                        </div>
                                    </div>
                                </div>


                                <div id="scopeOfWeekDay" class="mb-3 align-items-center">
                                    <input type="checkbox" name="forMonday" class="form-check-input" id="formForMonDay"
                                        <c:if test="${not empty forMonday}">checked</c:if>>
                                    <label class="form-check-label me-3" for="formForMonDay">月</label>
                                    <input type="checkbox" name="forTuesday" class="form-check-input"
                                        id="formForTuesday" <c:if test="${not empty forTuesday}">checked</c:if>>
                                    <label class="form-check-label me-3" for="formForTuesday">火</label>
                                    <input type="checkbox" name="forWednesday" class="form-check-input"
                                        id="formForWednesday" <c:if test="${not empty forWednesday}">checked</c:if>>
                                    <label class="form-check-label me-3" for="formForWednesday">水</label>
                                    <input type="checkbox" name="forThursday" class="form-check-input"
                                        id="formForThursday" <c:if test="${not empty forThursday}">checked</c:if>>
                                    <label class="form-check-label me-3" for="formForThursday">木</label>
                                    <input type="checkbox" name="forFriday" class="form-check-input" id="formForFriday"
                                        <c:if test="${not empty forFriday}">checked</c:if>>
                                    <label class="form-check-label me-3" for="formForFriday">金</label>
                                    <input type="checkbox" name="forSaturday" class="form-check-input"
                                        id="formForSaturday" <c:if test="${not empty forSaturday}">checked</c:if>>
                                    <label class="form-check-label me-3" for="formForSaturday">土</label>
                                    <input type="checkbox" name="forSunday" class="form-check-input" id="formForSunday"
                                        <c:if test="${not empty forSunday}">checked</c:if>>
                                    <label class="form-check-label me-3" for="formForSunday">日</label>
                                    <input type="checkbox" name="forHolidays" class="form-check-input"
                                        id="formForHlidays" <c:if test="${not empty forHolidays}">checked</c:if>>
                                    <label class="form-check-label" for="formForHlidays">祝日</label>
                                </div>
                            </div>
                        </div>

                    </fieldset>

                    <div class="mb-3">
                        <input type="button" class="btn btn-success" value="登録" onclick="submit()">
                        <a href="listPricePlan" class="btn btn-light">キャンセル</a>
                    </div>

                </form>
            </div>
        </div>
    </div>
    <script src="js/jquery-3.6.1.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script>
					$(document)
							.ready(
									function() {
										$("#formScopeSetting")
												.change(
														function() {
															if ($(this).prop(
																	"checked")) {
																$(
																		"#fieldOfScopeSettingItems")
																		.removeAttr(
																				"disabled");
															} else {
																$(
																		"#fieldOfScopeSettingItems")
																		.attr(
																				"disabled",
																				"disabled");
																$(
																		"#formScopeRoomType")
																		.val(0);
																$(
																		"#formScopeSex")
																		.val(0);
																$(
																		"#formScopeCustomerClass")
																		.val(0);
																$(
																		"#scopeOfWeekDay")
																		.children(
																				'input')
																		.removeAttr(
																				'checked')
																		.prop(
																				'checked',
																				false)
																		.change();
															}
														});
									});
				</script>
</body>
</html>
