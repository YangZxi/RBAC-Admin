(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-1c54a8c8"],{8269:function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-card",[a("search-bar",{attrs:{searchForm:e.searchForm},on:{"search-click":e.searchHandler}}),a("el-row",[a("data-table",{ref:"dataTable",attrs:{API:e.API,searchForm:e.searchForm}})],1)],1)],1)},n=[],r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-row",{staticStyle:{position:"sticky"},attrs:{type:"flex",justify:"space-between"}},[a("el-col",{attrs:{span:12}},[a("el-button",{attrs:{type:"primary",icon:"el-icon-circle-plus-outline",size:"small"},on:{click:e.addHandler}},[e._v("新增")]),a("el-button",{attrs:{type:"success",icon:"el-icon-edit",size:"small",disabled:1!=e.isOperater},on:{click:function(t){return e.modifyHandler(void 0)}}},[e._v("修改")]),a("el-button",{attrs:{type:"danger",icon:"el-icon-delete",size:"small",disabled:0==e.isOperater},on:{click:function(t){return e.removeHandler(void 0)}}},[e._v("删除")]),a("el-button",{attrs:{type:"warning",icon:"el-icon-document-add",size:"small",disabled:0==e.isOperater},on:{click:e.exportHandler}},[e._v("导出")])],1),a("el-col",{attrs:{span:3}},[a("el-button",{attrs:{type:"",plain:"",size:"mini",icon:"el-icon-refresh"},on:{click:function(t){return e.queryHandler(!0)}}}),a("el-button",{attrs:{type:"",plain:"",size:"mini",icon:"el-icon-full-screen"}})],1)],1),a("el-row",{attrs:{type:"flex",justify:"space-around",gutter:20}},[a("el-col",{attrs:{span:16}},[a("el-card",{staticClass:"box-card",attrs:{shadow:"never"}},[a("div",{staticClass:"clearfix",attrs:{slot:"header","body-style":"{margin:0}"},slot:"header"},[a("span",[a("b")])]),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.table.loading,expression:"table.loading"}],ref:"table",staticStyle:{width:"100%"},attrs:{data:e.pageData.records,"highlight-current-row":"","row-key":"id"},on:{"selection-change":e.selectionChange,"row-click":e.rowlClick,select:e.rowSelect}},[a("el-table-column",{attrs:{type:"selection",width:"55"}}),a("el-table-column",{attrs:{label:"角色名称",prop:"nameZh",fixed:"left",width:"200"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.nameZh))])]}}])}),a("el-table-column",{attrs:{label:"描述",prop:"desc",width:""}}),a("el-table-column",{attrs:{label:"角色名称(英文)",prop:"name",width:"150"}}),a("el-table-column",{attrs:{label:"状态",prop:"status",width:"100"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-switch",{attrs:{"active-value":1,"active-color":"#13ce66","inactive-value":0,"inactive-color":"#ff4949"},nativeOn:{click:function(a){return a.stopPropagation(),e.statusChange(t.row)}},model:{value:t.row.status,callback:function(a){e.$set(t.row,"status",a)},expression:"scope.row.status"}})]}}])}),a("el-table-column",{attrs:{label:"操作",width:"150",fixed:"right"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"mini"},on:{click:function(a){return a.stopPropagation(),e.modifyHandler(t.row)}}},[e._v("编辑")]),a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){return a.stopPropagation(),e.removeHandler(t.row)}}},[e._v("删除")])]}}])})],1),a("my-pagination",{attrs:{pageData:e.pageData,pageInfo:e.pageInfo},on:{queryHandler:e.queryHandler}})],1)],1),a("el-col",{attrs:{span:8}},[a("el-card",{staticClass:"box-card"},[a("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[a("span",[a("b",[e._v("权限分配")]),a("span",{staticStyle:{"font-size":"14px","margin-left":"10px"}},[e._v("\n                "+e._s(e.authority.role.nameZh)+"\n              ")])]),a("el-button",{staticStyle:{float:"right",padding:"3px 5px"},attrs:{size:"mini",type:"primary"},on:{click:e.addRoleMenus}},[e._v("保存")])],1),a("el-tree",{ref:"authorityTree",attrs:{data:e.authority.tree,"show-checkbox":!0,"node-key":"id","check-strictly":!0,"default-expanded-keys":[2],props:e.authority.defaultProps},on:{check:e.nodeCheckTrue}}),a("el-checkbox",{attrs:{indeterminate:e.authority.isIndeterminate},on:{change:function(t){e.authorityHandler().handleCheckAllChange()}},model:{value:e.authority.checkAll,callback:function(t){e.$set(e.authority,"checkAll",t)},expression:"authority.checkAll"}},[e._v("\n\t\t\t\t\t\t全选\n          ")])],1)],1)],1),a("el-dialog",{attrs:{title:e.dialog.title+"角色",visible:e.dialog.visible,"close-on-click-modal":!1,width:"40%"},on:{"update:visible":function(t){return e.$set(e.dialog,"visible",t)},open:e.queryRoles,close:e.dialogClose}},[a("el-form",{ref:"modelForm",attrs:{model:e.modelForm,rules:e.form.formRules,"status-icon":"","label-position":"right",size:"small","label-width":"80px"}},[a("el-row",{attrs:{type:"flex",justify:"space-between"}},[a("el-col",{staticStyle:{display:"none"},attrs:{span:24}},[a("el-form-item",{attrs:{label:"id"}},[a("el-input",{model:{value:e.modelForm.id,callback:function(t){e.$set(e.modelForm,"id",t)},expression:"modelForm.id"}})],1)],1),a("el-col",{attrs:{span:24}},[a("el-form-item",{attrs:{label:"角色名称",prop:"nameZh"}},[a("el-input",{attrs:{placeholder:"请输入角色名称"},model:{value:e.modelForm.nameZh,callback:function(t){e.$set(e.modelForm,"nameZh",t)},expression:"modelForm.nameZh"}})],1)],1),a("el-col",{attrs:{span:24}},[a("el-form-item",{attrs:{label:"英文名称",prop:"name"}},[a("el-input",{attrs:{placeholder:"'请输入角色英文名称"},model:{value:e.modelForm.name,callback:function(t){e.$set(e.modelForm,"name",t)},expression:"modelForm.name"}},[a("template",{slot:"prepend"},[e._v("ROLE_")])],2)],1)],1),a("el-col",{attrs:{span:24}},[a("el-form-item",{attrs:{label:"状态",prop:"status"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"默认状态为启用"},model:{value:e.modelForm.status,callback:function(t){e.$set(e.modelForm,"status",t)},expression:"modelForm.status"}},[a("el-option",{attrs:{label:"启用",value:1}}),a("el-option",{attrs:{label:"禁用",value:0}})],1)],1)],1),a("el-col",{attrs:{span:24}},[a("el-form-item",{attrs:{label:"描述",prop:"desc"}},[a("el-input",{attrs:{type:"textarea",autosize:{minRows:2,maxRows:4},placeholder:"请输入角色描述信息"},model:{value:e.modelForm.desc,callback:function(t){e.$set(e.modelForm,"desc",t)},expression:"modelForm.desc"}})],1)],1)],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"medium"},on:{click:function(t){e.dialog.visible=!1}}},[e._v("取 消")]),a("el-button",{attrs:{size:"medium"},on:{click:function(t){return e.resetForm()}}},[e._v("重 置")]),a("el-button",{attrs:{type:"primary",size:"medium"},on:{click:function(t){return e.submitHandler(0)}}},[e._v(e._s(e.dialog.submitText))])],1)],1)],1)},i=[],s=(a("aa18"),a("982e"),a("1bc78"),a("6a61"),a("60b5")),l={name:"RoleTable",props:{searchForm:Object,API:String},data:function(){return{checkedKeys:[],isOperater:0,table:{loading:!0,rows:[]},authority:{role:{id:null},tree:[],selected:[],defaultProps:{children:"children",label:"name"},checkAll:!1,isIndeterminate:!0},dialog:{visible:!1,title:"",submitText:"提交"},pageInfo:{current:1,size:10,total:0,parentMenuId:0},modelForm:{id:null,name:null,nameZh:null,desc:null,status:1},form:{roles:[],originalModel:{},formRules:{nameZh:[{required:!0,message:"角色名称不可以为空",trigger:"blur"},{min:2,max:10,message:"长度在2-10个字符之间",trigger:"blur"}],name:[{required:!0,message:"角色英文名称不可以为空",trigger:"blur"},{min:3,max:10,message:"长度在3-10个字符",trigger:"blur"}],desc:[{max:50,message:"最大长度50个字符"}]}},pageData:{current:1,total:0,records:[]},method:this.$axios.get}},methods:{onSelect:function(e,t){console.log(e)},statusChange:function(){var e=Object(s["a"])(regeneratorRuntime.mark((function e(t){var a=this;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:if(this.method=this.$axios.post,1==t.status){e.next=8;break}return e.next=4,this.$alert("是否将当前【"+t.nameZh+"】设置为禁用状态","禁用角色",{distinguishCancelAndClose:!0,confirmButtonText:"禁用",cancelButtonText:"取消"}).then((function(){t.status=0})).catch((function(e){t.status=1}));case 4:if(1!=t.status){e.next=6;break}return e.abrupt("return");case 6:e.next=9;break;case 8:t.status=1;case 9:this.baseRequest({id:t.id,status:t.status},!1).then((function(e){200==e.code&&a.$notify({title:"修改角色“"+t.nameZh+"”状态成功",type:"success"})})).catch((function(e){t.status=0==t.status?1:0,a.$notify({title:"修改出现错误",type:"error"})}));case 10:case"end":return e.stop()}}),e,this)})));function t(t){return e.apply(this,arguments)}return t}(),selectionChange:function(e){this.isOperater=e.length,this.table.rows=e},rowSelect:function(e,t){console.log(e,t)},rowlClick:function(e){this.getRoleMenu(e)},authorityHandler:function(){var e=this;return{handleCheckAllChange:function(){var t=e.authority.checkAll?e.authority.tree.map((function(e){return e.id})):[];e.$refs.authorityTree.setCheckedKeys(t)}}},nodeCheck:function(e,t){},nodeCheckTrue:function(e,t){var a=this,o=function e(t,o){null!=t.children&&0!=t.children.length&&t.children.forEach((function(t){return a.$refs.authorityTree.setChecked(t.id,o),e(t,o)}))},n=!1;if(t.checkedKeys.includes(e.id)){n=!0;var r=e;while(r.parentMenuId)this.$refs.authorityTree.setChecked(r.id,n),r=this.$store.state.menuModule.menusOriginal[r.parentMenuId]}o(e,n)},getRoleMenu:function(e){var t=this;this.authority.role=e,this.$axios.get(this.$api.ROLE_MENU_API,{roleId:e.id},!1).then((function(e){t.$refs.authorityTree.setCheckedKeys(e.data),t.authority.selected=e.data}))},addRoleMenus:function(){if(this.authority.role&&this.authority.role.id){this.method=this.$axios.post;this.$refs.authorityTree.getCheckedNodes(!1,!0).map((function(e){return e.id}));this.baseRequest({id:this.authority.role.id,menuIds:this.$refs.authorityTree.getCheckedKeys()})}else this.$message.warning("请先选择你需要赋权的角色")},baseRequest:function(){var e=Object(s["a"])(regeneratorRuntime.mark((function e(t){var a,o=arguments;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return a=!(o.length>1&&void 0!==o[1])||o[1],console.log(this.API),e.next=4,this.method(this.API,t,a).then((function(e){return e.code,e})).catch((function(e){Promise.reject(e)}));case 4:return e.abrupt("return",e.sent);case 5:case"end":return e.stop()}}),e,this)})));function t(t){return e.apply(this,arguments)}return t}(),queryHandler:function(e){var t=this;e&&(this.authority.role={},this.pageInfo.current=e),this.table.loading=!0,this.$log.info(this.searchForm),this.$axios.get(this.API,Object.assign({},this.pageInfo,this.searchForm)).then((function(e){t.pageData=e.data?e.data:{current:1,total:0,records:[]},t.table.loading=!1})).catch((function(e){t.table.loading=!1}))},addHandler:function(){this.dialog.title="新增",this.dialog.submitText="新增",this.dialog.visible=!0,this.method=this.$axios.put},modifyHandler:function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:this.table.rows.slice(-1)[0];this.modelForm=e,this.form.originalModel=JSON.parse(JSON.stringify(e)),this.dialog.title="修改",this.dialog.submitText="更新",this.dialog.visible=!0,this.method=this.$axios.post},removeHandler:function(){var e=this,t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:this.table.rows;this.$alert("本次操作一经确认将无法撤回，是否继续","角色删除",{distinguishCancelAndClose:!0,confirmButtonText:"删除",cancelButtonText:"取消"}).then((function(){e.method=e.$axios.delete,Array.isArray(t)?e.baseRequest(t.map((function(e){return e.id}))).then((function(t){return e.queryHandler()})).catch(err>={}):e.baseRequest([t.id]).then((function(t){return e.queryHandler()})).catch(err>={})})).catch((function(e){}))},exportHandler:function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:this.table.rows;console.log(e),console.log(e)},submitHandler:function(e){var t=this;console.log(this.modelForm),this.$refs["modelForm"].validate((function(e){if(!e)return!1;t.method(t.API,t.modelForm).then((function(e){200==e.code&&(t.form.originalModel=null,t.dialog.visible=!1)}))}))},resetForm:function(){this.modelForm={}},typeChange:function(e){console.log(e)},queryRoles:function(){var e=this;0==this.form.roles.length&&this.$axios.get(this.$api.ROLE_API,{size:20},!1).then((function(t){e.form.roles=t.data.records}))},dialogClose:function(){null!=this.form.originalModel&&this.$tools.copyObj(this.modelForm,this.form.originalModel,this),this.modelForm={}}},computed:{},mounted:function(){this.queryHandler(),this.authority.tree=this.$store.state.menuModule.menus},watch:{pageInfo:function(){console.log("我变了")}}},c=l,u=(a("f41e"),a("5d22")),d=Object(u["a"])(c,r,i,!1,null,null,null),h=d.exports,m={data:function(){return{searchForm:{word:null,startTime:null,endTime:null,status:1},API:this.$api.ROLE_API}},methods:{searchHandler:function(e){null!=e.dateScope?(this.searchForm.startTime=e.dateScope[0],this.searchForm.endTime=e.dateScope[1]):(this.searchForm.startTime=null,this.searchForm.endTime=null),this.$refs.dataTable.queryHandler(1)}},mounted:function(){},components:{DataTable:h}},f=m,p=Object(u["a"])(f,o,n,!1,null,null,null);t["default"]=p.exports},c830:function(e,t,a){},f41e:function(e,t,a){"use strict";a("c830")}}]);