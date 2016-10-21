<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html class="js">
<head>
    <%@include file="slices/head.jsp" %>
</head>
<body>

<div class="container">
    <%@include file="slices/nav.jsp" %>

    <div class="col-xs-12">
        <h1>Pokemons</h1>
    </div>
    <div class="col-xs-12 col-md-8">
        <table class="table table-hover table-condensed">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Type</th>
                    <th>&nbsp;</th>
                </tr>
            </thead>
            <tbody id="pokemons-list">

            </tbody>
        </table>
    </div>

    <div class="col-xs-12 col-md-4">
        <h2>Add Pokemon</h2>
        <form id="add-pokeform">
            <div class="form-group">
                <label for="add-pokemon-name">Name</label>
                <input class="form-control" type="text" name="name" id="add-pokemon-name"/>
            </div>
            <div class="form-group">
                <label for="add-pokemon-type">Type</label>
                <input class="form-control" type="text" name="type" id="add-pokemon-type"/>
            </div>
            <button type="submit" class="btn btn-primary">Add</button>
        </form>
    </div>
</div>

<div class="modal fade" id="editPokemon" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Edit Pokemon</h4>
            </div>
            <div class="modal-body">
                <form id="edit-pokeform">
                    <input type="hidden" name="id" id="edit-pokemon-id" />
                    <div class="form-group">
                        <label for="add-pokemon-name">Name</label>
                        <input class="form-control" type="text" name="name" id="edit-pokemon-name" />
                    </div>
                    <div class="form-group">
                        <label for="add-pokemon-type">Type</label>
                        <input class="form-control" type="text" name="type" id="edit-pokemon-type" />
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-primary" onclick="savePokemon()">Save</button>
            </div>
        </div>
    </div>
</div>

<!-- All the scripts -->
<%@include file="slices/scripts.jsp" %>
<script type="text/javascript">

    function refreshPokemons() {
        $.get('${base}/api/pokemons', function(data) {
            var $table = $('#pokemons-list');
            $table.empty();
            data.forEach(function(row) {
                var $row = $('<tr>' +
                        '<td>' + row.name + '</td>' +
                        '<td>' + row.type + '</td>' +
                        '<td><div class="pull-right btn-group">' +
                            '<a class="btn btn-default" onclick="deletePokemon('+row.id+')"><span class="ion ion-trash-a"></span></a>' +
                            '<a class="btn btn-default" onclick="editPokemon('+row.id+')"><span class="ion ion-edit"></span></a>' +
                        '</div></td>' +
                        '</tr>');

                $table.append($row);
            });
        });
    }

    function addPokemon(event) {

        event.preventDefault();

        var $data = {
            "name": $('#add-pokemon-name').val(),
            "type": $('#add-pokemon-type').val()
        };

        $.ajax({
            url: '${base}/api/pokemons',
            method: 'POST',
            data: JSON.stringify($data),
            contentType: 'application/json'
        }).then(function() {
            refreshPokemons();
        });
    }

    function editPokemon(id) {
        console.log("Editing", id);

        $.get('${base}/api/pokemons/' + id).then(function(data) {
            $('#edit-pokemon-id').val(data.id);
            $('#edit-pokemon-name').val(data.name);
            $('#edit-pokemon-type').val(data.type);

            $('#editPokemon').modal('show');
        });
    }

    function savePokemon() {
        var $id = $('#edit-pokemon-id').val();
        var $data = {
            "name": $('#edit-pokemon-name').val(),
            "type": $('#edit-pokemon-type').val()
        };

        $.ajax({
            url: '${base}/api/pokemons/' + $id,
            method: 'PUT',
            data: JSON.stringify($data),
            contentType: 'application/json'
        }).then(function() {
            console.log("Edited", $id);
            $('#editPokemon').modal('hide');
            refreshPokemons();
        });
    }

    function deletePokemon(id) {
        $.ajax({
            url: '${base}/api/pokemons/' + id,
            method: 'DELETE'
        }).then(function() {
            console.log("Deleted", id);
            refreshPokemons();
        });
    }

    $(function() {
        refreshPokemons();

        $('#add-pokeform').submit(addPokemon);
    });
</script>
<!-- All the scripts Ends-->
</body>
</html>