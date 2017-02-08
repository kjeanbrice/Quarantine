<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- [START_EXCLUDE] -->
<%--
  ~ Copyright (c) 2016 Google Inc. All Rights Reserved.
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License"); you
  ~ may not use this file except in compliance with the License. You may
  ~ obtain a copy of the License at
  ~
  ~     http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
  ~ implied. See the License for the specific language governing
  ~ permissions and limitations under the License.
  --%>
<!-- [END_EXCLUDE] -->
<html>
<head>
  <link rel="stylesheet" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="webresources/css/index_styles.css">
  <title>To Do List Maker</title>
</head>
<body class="background-app ">
<div id="top"></div>
<div id="bottom"></div>
<div id="left"></div>
<div id="right"></div>

<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Quarantine</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active">
        <a href="#" title="Create a new to do list"><span class="glyphicon glyphicon-folder-close"></span> New
          List</a>
      </li>

      <li>
        <a href="#" title="Load an existing to do list"><span class="glyphicon glyphicon-folder-open"></span>
          Load List</a>
      </li>
      <li>
        <a href="#" title="Save to do list"><span class="glyphicon glyphicon-save"></span> Save List</a>
      </li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
    </ul>
  </div>
</nav>

<div class="container">
  <div class="row-padding row">
    <span class="col-md-2 heading-label">To Do List</span>
  </div>


  <div class=" form-group border-pane">
    <div class="row">
      <span class="col-md-2 subheading-label">Details</span>
    </div>
    <div class="style-glow">
      <div class="row">
        <div class="col-md-2">
          <label for="list_name" class="input-label col-form-label">List name: </label>
        </div>
        <div class="col-md-2">
          <input type="text" id="list_name">
        </div>
        <div class="col-md-8"></div>
      </div>
      <div class = "row">
        <div class="col-md-2">
          <label for="owner_name" class="input-label col-form-label">Owner: </label>
        </div>
        <div class="col-md-2">
          <input type="text" id="owner_name">
        </div>
        <div class="col-md-8"></div>
      </div>
    </div>
  </div>

  <div class="form-group border-pane">
    <div class="row">
      <span class="col-md-2 subheading-label subheading-padding">Items</span>
    </div>
    <div class="row subheading-padding">
      <div class="col-md-6">
        <button type="button" class="btn btn-secondary">
          <span class="glyphicon glyphicon-plus-sign"></span>
        </button>
        <button type="button" class="btn btn-secondary">
          <span class="glyphicon glyphicon-minus-sign"></span>
        </button>
        <button type="button" class="btn btn-secondary">
          <span class="glyphicon glyphicon-circle-arrow-up"></span>
        </button>
        <button type="button" class="btn btn-secondary">
          <span class="glyphicon glyphicon-circle-arrow-down"></span>
        </button>
      </div>
    </div>


    <div class="row">
      <div class="col-md-12">
        <table class="table table-bordered table-" width="100%" cellspacing="0">
          <thead>
          <tr>
            <th>Category</th>
            <th>Description</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Completed</th>
          </tr>
          </thead>
          <tbody class="tbody-default">
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>

          </tbody>
        </table>
      </div>
    </div>

  </div>
</div>
</body>

<script src="https://code.jquery.com/jquery-3.1.1.min.js" type="text/javascript"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
</html>
