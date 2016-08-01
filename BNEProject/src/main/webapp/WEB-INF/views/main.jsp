<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-content-wrap">
	<div class="row">
		<div class="col-md-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">할일</h3>
				</div>
				<div class="panel-body"></div>
			</div>

			<!-- END LINE CHART -->

		</div>
		<div class="col-md-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">결제/미결제</h3>
				</div>
				<div class="panel-body"></div>
			</div>
			<!-- START BAR CHART -->

			<!-- END Area CHART -->

		</div>

	</div>
	<div class="row">
		<div class="col-md-4">

			<!-- START BAR CHART -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">팀원별 실적</h3>
				</div>
				<div class="panel-body">
					<div id="morris-bar-example1" style="height: 300px;"></div>

				</div>
			</div>
			<!-- END LINE CHART -->

		</div>
		<div class="col-md-4">

			<!-- START BAR CHART -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">매출</h3>
				</div>
				<div class="panel-body">
					<div id="morris-line-example" style="height: 300px;"></div>
				</div>
			</div>
			<!-- END Area CHART -->

		</div>
		<div class="col-md-4">

			<!-- START DONUT CHART -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">달성률</h3>
				</div>
				<div class="panel-body">
					<div id="morris-donut-example" style="height: 300px;"></div>
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
<script type="text/javascript">mainpageMorrisCharts();</script>

