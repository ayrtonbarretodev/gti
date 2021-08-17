//datatables - lista de equipamentos
$(document).ready(function() {
    moment.locale('pt-BR');
    var table = $('#table-equipamentos').DataTable({
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
        lengthMenu : [ 5, 10, 15, 20],
        processing : true,
        serverSide : true,
        responsive : true,
        //scrollX: true,
        ajax : {
            url : '/equipamentos/datatables/server/equipamentos',
            data : 'data'
        },
        columns : [
            {data : 'id'},
            {data : 'nome'},
            {data : 'tomboPatrimonial'},
            {data : 'status'},
            {data : 'categoria.nome'},
            {data : 'departamento.nome'},
            {data : 'enderecoMac'},
            {	data : 'id',
                render : function(id) {
                    return ''.concat('<a class="btn btn-success btn-sm btn-block"', ' ')
                        .concat('href="').concat('/equipamentos/editar/').concat(id, '"', ' ')
                        .concat('role="button" title="Editar" data-toggle="tooltip" data-placement="right">', ' ')
                        .concat('<i class="fas fa-edit"></i></a>');
                },
                orderable : false
            },
            {	data : 'id',
                render : function(id) {
                    return ''.concat('<a class="btn btn-info btn-sm btn-block"', ' ')
                        .concat('href="').concat('/equipamentos/excluir/').concat(id, '"', ' ')
                        .concat('role="button" title="Excluir" data-toggle="tooltip" data-placement="right">', ' ')
                        .concat('<i class="fas fa-trash-alt"></i></a>');
                },
                orderable : false
            },
            {data : 'dataEntrada', render: function (dataEntrada) {
                    return moment(dataEntrada).format('L');
                }},
            {data : 'modelo'},
            {data : 'fabricante'},
            {data : 'numeroSerie'},
        ]
    });
});