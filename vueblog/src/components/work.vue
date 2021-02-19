<template>
  <el-container>
    <el-header class="cate_mana_header">
      <el-input
        placeholder="请输入作品名称"
        v-model="title" style="width: 200px;">
      </el-input>
      <el-input
        placeholder="简单描述作品,不超过20个字"
        v-model="brief" style="width: 400px;margin-left:20px" >
      </el-input>
      <file-upload ref="fileUpload1"  :visible.sync="visible" :action="uploadAction" @refresh-table="" :data="listQuery" />
      <el-button type="primary" size="medium" style="margin-left: 10px" @click="addNewWork">新增作品</el-button>
    </el-header>
    <el-main class="cate_mana_main">
      <el-table
        ref="multipleTable"
        :data="works"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange" v-loading="loading">
        <el-table-column
          type="selection"
          width="55" align="left">
        </el-table-column>
        <el-table-column
          label="编号"
          prop="workCode"
          width="120" align="left">
        </el-table-column>
        <el-table-column
          label="作品名称"
          prop="title"
          width="120" align="left">
        </el-table-column>
        <el-table-column
          prop="brief"
          label="作品描述" align="left">
        </el-table-column>

        <el-table-column label="操作" align="left">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="showUpload(scope.$index, scope.row)">上传文件
            </el-button>
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">编辑
            </el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-button type="danger" :disabled="this.selItems.length==0" style="margin-top: 10px;width: 100px;"
                 @click="deleteAll" v-if="this.works.length>0">批量删除
      </el-button>
    </el-main>
  </el-container>
</template>
<script>
  import FileUpload from '../components/FileUpload'

  import {postRequest} from '../utils/api'
  import {putRequest} from '../utils/api'
  import {deleteRequest} from '../utils/api'
  import {getRequest} from '../utils/api'
  export default{
    components:{
       'file-upload': FileUpload
    },
    props:{
      workCode:{
        type:String
      },brief:{
        type:String
      },

      visible: {//对话框的开关
        type: Boolean,
        default: false
      },
      uploadAction: {//后台地址
        type: String,
        default: '/file_upload'
      },
      listQuery:{
        type: Object
      }
    },
    methods: {
      addNewWork(){
        this.loading = true;
        var _this = this;
        postRequest('/user/work/', {title: this.title,brief:this.brief}).then(function(resp) {
          if (resp.status == 200) {
            var json = resp.data;
            _this.$message({type: json.status, message: json.msg});
            _this.title = '';
            _this.brief='';
            _this.refresh();
          }
          _this.loading = false;
        },function(resp) {
          if (resp.response.status == 403) {
            _this.$message({
              type: 'error',
              message: resp.response.data
            });
          }
          _this.loading = false;
        });
      },
      deleteAll(){
        var _this = this;
        this.$confirm('确认删除这 ' + this.selItems.length + ' 条数据?', '提示', {
          type: 'warning',
          confirmButtonText: '确定',
          cancelButtonText: '取消'
        }).then(()=> {
          var selItems = _this.selItems;
          var ids = '';
          for (var i = 0; i < selItems.length; i++) {
            ids += selItems[i].id + ",";
          }
          _this.deleteCate(ids.substring(0, ids.length - 1));
        }).catch(() => {
          //取消
          _this.loading = false;
        });
      },
      handleSelectionChange(val) {
        this.selItems = val;
      },
      handleEdit(index, row){
        var _this = this;
        this.$prompt('请输入新名称', '编辑', {
          confirmButtonText: '更新',
          inputValue: row.title,
          cancelButtonText: '取消'
        }).then(({value}) => {
          //value就是输入值
          if (value == null || value.length == 0) {
            _this.$message({
              type: 'info',
              message: '数据不能为空!'
            });
          } else {
            _this.loading = true;
            putRequest("/admin/category/", {id: row.id, cateName: value}).then(function(resp){
              var json = resp.data;
              _this.$message({
                type: json.status,
                message: json.msg
              });
              _this.refresh();
            },function(resp) {
              if (resp.response.status == 403) {
                _this.$message({
                  type: 'error',
                  message: resp.response.data
                });
              }
              _this.loading = false;
            });
          }
        });
      },
      handleDelete(index, row){
        let _this = this;
        this.$confirm('确认删除 ' + row.title + ' ?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function(){
          _this.deleteCate(row.id);
        }).catch(function(){
          //取消
          _this.loading = false;
        });
      },
      deleteCate(ids){
        var _this = this;
        this.loading = true;
        //删除
        deleteRequest("/user/work/" + ids).then(function(resp){
          var json = resp.data;
          _this.$message({
            type: json.status,
            message: json.msg
          });
          _this.refresh();
        }, function(resp){
          _this.loading = false;
          if (resp.response.status == 403) {
            _this.$message({
              type: 'error',
              message: resp.response.data
            });
          } else if (resp.response.status == 500) {
            _this.$message({
              type: 'error',
              message: '作品删除失败!'
            });
          }
        })
      },
      showUpload(index,row){//展示上传组件
        let _this = this;
        _this.visible = true;
        _this.listQuery.workCode = row.workCode;
      },
      refresh(){
        let _this = this;
        getRequest("/user/work/all").then(function(resp){
          _this.works = resp.data.works;
          _this.loading = false;
        },function(resp) {
          if (resp.response.status == 403) {
            _this.$message({
              type: 'error',
              message: resp.response.data
            });
          }
          _this.loading = false;
        });
      }
    },
    mounted: function () {
      this.loading = true;
      this.refresh();
    },
    data(){
      return {
        title: 'abctitle',
        selItems: [],
        works: [],
        loading: true
      }
    }
  }
</script>
<style>
  .cate_mana_header {
    background-color: #ececec;
    margin-top: 20px;
    padding-left: 5px;
    display: flex;
    justify-content: flex-start;
  }

  .cate_mana_main {
    /*justify-content: flex-start;*/
    display: flex;
    flex-direction: column;
    padding-left: 5px;
    background-color: #ececec;
    margin-top: 20px;
    padding-top: 10px;
  }
</style>
