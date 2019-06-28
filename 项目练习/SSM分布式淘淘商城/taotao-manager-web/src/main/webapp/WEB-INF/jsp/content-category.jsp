<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	 <ul id="contentCategory" class="easyui-tree">  </ul>
</div>
<div id="contentCategoryMenu" class="easyui-menu" style="width:120px;" data-options="onClick:menuHandler">
    <div data-options="iconCls:'icon-add',name:'add'">添加</div>
    <div data-options="iconCls:'icon-remove',name:'rename'">重命名</div>
    <div class="menu-sep"></div>
    <div data-options="iconCls:'icon-remove',name:'delete'">删除</div>
</div>
<script type="text/javascript">
$(function(){
	//在#contentCategory 所在的标签创建树节点
	$("#contentCategory").tree({
		url : '/content/category/list',
		animate: true,
		method : "GET",
		//右击鼠标触发事件
		onContextMenu: function(e,node){
			//关闭原来的鼠标默认事件
            e.preventDefault();
			//选中右击鼠标的当前节点
            $(this).tree('select',node.target);
			//展示菜单栏
            $('#contentCategoryMenu').menu('show',{
                left: e.pageX,
                top: e.pageY
            });
        },
        //在编辑之后触发
        onAfterEdit : function(node){
        	var _tree = $(this);
        	
        	//id==0 表示新增节点
        	if(node.id == 0){
        		// 新增节点
        		/*
        		{parentId:node.parentId,name:node.text}
        		parentId:父节点id
        		*/
        		$.post("/content/category/create",{parentId:node.parentId,name:node.text},function(data){
        			if(data.status == 200){
        				_tree.tree("update",{
            				target : node.target,//更新哪一个节点
            				id : data.data.id//更新新增节点id
            			});
        			}else{
        				$.messager.alert('提示','创建'+node.text+' 分类失败!');
        			}
        		});
        	}else{
        		$.post("/content/category/update",{id:node.id,name:node.text},function(data){
        			$.messager.alert('提示',data);
        		});
        	}
        }
	});
});
//处理点击菜单事件
function menuHandler(item){
	//获取树
	var tree = $("#contentCategory");
	//获取被选中的节点
	var node = tree.tree("getSelected");
	//1==1 true 1=="1" true ;
	//1===1 true 1==="1" false
	//点击添加
	if(item.name === "add"){
		tree.tree('append', {
            parent: (node?node.target:null),//被添加的子节点的父节点
            data: [{
                text: '新建分类',//节点内容
                id : 0,//节点id=0
                parentId : node.id
            }]
        }); 
		var _node = tree.tree('find',0);//id=0 ==>>根节点
		//选中后开启编辑
		tree.tree("select",_node.target).tree('beginEdit',_node.target);
	}else if(item.name === "rename"){
		tree.tree('beginEdit',node.target);
	}else if(item.name === "delete"){
		$.messager.confirm('确认','确定删除名为 '+node.text+' 的分类吗？',function(r){
			if(r){
				$.post("/content/category/delete/",{id:node.id},function(data){
					if(data=="删除成功")tree.tree("remove",node.target);
					$.messager.alert('提示',data);
				});	
			}
		});
	}
}
</script>