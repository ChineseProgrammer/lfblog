<template>
  <div class="test">
    <el-table-column prop="year">
      年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份:&nbsp;&nbsp;&nbsp;&nbsp;<el-input placeholder="请输入年份" v-model="year" style="width: 200px;"></el-input>
    </el-table-column>

    <el-table-column prop="riskLabelIdList">
      风险标签:&nbsp;&nbsp;&nbsp;&nbsp;<el-input placeholder="请输入风险标签" v-model="riskLabelIdList" style="width: 200px;"></el-input>
    </el-table-column>

    <el-table-column prop="ppdmCde">
      辖&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;区:&nbsp;&nbsp;&nbsp;&nbsp;<el-input placeholder="请输入辖区代码" v-model="ppdmCde" style="width: 200px;"></el-input>
    </el-table-column>

    <div class="comp">
      <form>
        <el-table-column prop="compName">
          公司名称:&nbsp;&nbsp;&nbsp;&nbsp;<el-input placeholder="请输入公司名称" v-model="compName" style="width: 200px;"></el-input>
        </el-table-column>
        <el-table-column prop="secCde">
          证券代码:&nbsp;&nbsp;&nbsp;&nbsp;<el-input placeholder="请输入证券代码" v-model="secCde" style="width: 200px;"></el-input>
        </el-table-column>
        <el-table-column prop="secName">
          证券简称:&nbsp;&nbsp;&nbsp;&nbsp;<el-input placeholder="请输入证券简称" v-model="secName" style="width: 200px;"></el-input>
        </el-table-column>
        <el-table-column prop="pubBoardCde">
          板&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;块:&nbsp;&nbsp;&nbsp;&nbsp;<el-input placeholder="请输入板块代码" v-model="pubBoardCde" style="width: 200px;"></el-input>
        </el-table-column>
        <el-table-column prop="idstrClassCde">
          行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业:&nbsp;&nbsp;&nbsp;&nbsp;<el-input placeholder="请输入行业代码" v-model="idstrClassCde" style="width: 200px;"></el-input>
        </el-table-column>
      </form>
    </div>
  <div class="af">
    <form>
      <el-table-column prop="afName">
        事&nbsp;务&nbsp;所:&nbsp;&nbsp;&nbsp;&nbsp;<el-input placeholder="请输入事务所名称" v-model="afName" style="width: 200px;"></el-input>
      </el-table-column>

    </form>
  </div>
  <div class="cpa">
    <form>
      <el-table-column prop="cpaName">
        姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:&nbsp;&nbsp;&nbsp;&nbsp;<el-input placeholder="请输入姓名" v-model="cpaName" style="width: 200px;"></el-input>
      </el-table-column>
    </form>
    <br/>
    <el-button type="primary" @click="addNewCate('findCompList')">上市公司检索</el-button>
    <el-button type="success" @click="addNewCate('findAfList')">事务所检索</el-button>
    <el-button type="info" @click="addNewCate('findCpaList')">注册会计师检索</el-button><br/><br/>
    <el-button type="warning" @click="addNewCate('exportCompList')">上市公司导出</el-button>
    <el-button type="danger" @click="addNewCate('exportAfList')">事务所导出</el-button>
    <el-button @click="addNewCate('exportCpaList')">注册会计师导出</el-button>
  </div>
    <div id="result">
        {{resJson}}
    </div>
  </div>
</template>
<script>
  import {postRequest} from '../utils/api'
  import {putRequest} from '../utils/api'
  import {deleteRequest} from '../utils/api'
  import {getRequest} from '../utils/api'
  export default{
    props:{
      'resJson':''
    },
    methods: {
      addNewCate(_actionName){
        this.loading = true;
        var _this = this;
        var _param_cpa = {
            cpaName: this.cpaName,
            year:this.year,
            riskLabelIdList:this.riskLabelIdList,
            pageNo:1,
            pageSize:10
        };

        var _param_af = {
            afName: this.cpaName,
            year:this.year,
            ppdmCde:this.ppdmCde,
            riskLabelIdList:this.riskLabelIdList,
            pageNo:1,
            pageSize:10
        };
        var _param_comp = {
            compName: this.compName,
            year:this.year,
            secCde:this.secCde,
            secName:this.secName,
            pubBoardCde:this.pubBoardCde,
            idstrClassCde:this.idstrClassCde,
            ppdmCde:this.ppdmCde,
            riskLabelIdList:this.riskLabelIdList,
            pageNo:1,
            pageSize:10
        };
        var _jsessionid = 'A697057DE1C973069131150561F3D1C0';
        var _url = 'http://localhost:8080/accs-web/indexListSec/'+_actionName+';jsessionid='+_jsessionid;
        postRequest(_url, {
              cpaName: this.cpaName,
              year:this.year,
              riskLabelIdList:this.riskLabelIdList,
              pageNo:1,
              pageSize:10
          }
        ).then(resp=> {
            var _data = resp.data.data;
            _this.resJson = _data;
        }, resp=> {
          if (resp.response.status == 403) {
            alert('403了')
          }
         alert('失败了')
        });
      }
    }
  }
</script>
