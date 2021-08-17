//datatables - lista de categorias
$(document).ready(function() {
    moment.locale('pt-BR');
    var table = $('#table-categorias').DataTable({
        "language":{
            "lengthMenu": "Mostrar _MENU_ registros por página",
            "infoEmpty": "Nenhum registro disponível",
            "zeroRecords": "Nenhum registro encontrado",
            "info": "Mostrando página _PAGE_ de _PAGES_",
            "search": "Pesquisar:",
            "paginate": {
                "previous": "Anterior",
                "next":"Próxima"
            }
        },
        searching : true,
        lengthMenu : [ 5, 10 ], //número de itens por página
        processing : true,
        serverSide : true, // habilita a datatables para trabalhar no lado servidor
        responsive : true, // tabela com comportamento responsivo
        //scrollX: true,
        ajax : {
            url : '/categorias/datatables/server/categorias',
            data : 'data' //parametro que recebe os dados
        },
        columns : [
            {data : 'id'},
            {data : 'nome'},
            {	data : 'id',
                render : function(id) {
                    return ''.concat('<a class="btn btn-success btn-sm btn-block"', ' ')
                        .concat('href="').concat('/categorias/editar/').concat(id, '"', ' ')
                        .concat('role="button" title="Editar" data-toggle="tooltip" data-placement="right">', ' ')
                        .concat('<i class="fas fa-edit"></i></a>');
                },
                orderable : false
            },
            {	data : 'id',
                render : function(id) {
                    return ''.concat('<a class="btn btn-info btn-sm btn-block"', ' ')
                        .concat('href="').concat('/categorias/excluir/').concat(id, '"', ' ')
                        .concat('role="button" title="Excluir" data-toggle="tooltip" data-placement="right">', ' ')
                        .concat('<i class="fas fa-trash-alt"></i></a>');
                },
                orderable : false
            }
        ]
    });

    $('.btn-block').on('click', function (event){
        var href = $(this).attr('href');
        $('#myModal #ok_confirm').attr('href', href)
        $('#myModal').modal();
    });
});