<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-content-wrap">
	
	<div class="row">
		<div class="col-md-4">

			<!-- START BAR CHART -->
			<div class="panel panel-default">
				<div class="panel-heading">
					
					<c:if test="${sessionScope.user.position == 'manager'}">
					<span class="pull-right">
					<select class="form-control" name="member_id" id="member_id">
							<option value="" disabled selected hidden="true">${sessionScope.user.employee_name}</option>
						<c:forEach items="${requestScope.member}" var="employee">
							<option value="${employee.employee_id}">${employee.employee_name}</option>
      					</c:forEach>
					</select>
					</span>
					</c:if>
					<h3 class="panel-title">주행거리 </h3>
					
				</div>
				<div class="panel-body">
					<div id="morris-bar-gaugeChart" style="height: 300px;"></div>

				</div>
			</div>
			<!-- END LINE CHART -->

		</div>
		<div class="col-md-4">

			<!-- START BAR CHART -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">거래처별매출</h3>
				</div>
				<div class="panel-body">
					 <div class="row stacked" id="customerSales" style="height: 300px;" >
                   	 </div>                               
				</div>
			</div>
			<!-- END Area CHART -->

		</div>
		<div class="col-md-4">

			<!-- START DONUT CHART -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">뭘더넣어볼까?</h3>
				</div>
				<div class="panel-body">
					<div id="" style="height: 300px;"></div>
				</div>
			</div>
			<!-- END DONUT CHART -->

		</div>
	</div>
</div>
<script type="text/javascript" src="/js/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript" src="/js/plugins/jquery/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/plugins/morris/raphael-min.js"></script>
<script type="text/javascript" src="/js/plugins/morris/morris.min.js"></script>
<script type="text/javascript" src="/js/graphData.js"></script>
<script type="text/javascript">gaugeDistance(); customerSales();</script>

