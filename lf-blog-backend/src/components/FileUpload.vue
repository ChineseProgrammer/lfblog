<template>
  <div class="upload-container">
    <el-dialog v-loading="loading" :title="title" :visible.sync="visible" width="70%" :before-close="beforeClose">
      <el-upload ref="fileUpload" class="upload-demo" drag :action="action" :data="data" :headers="headers" :multiple="false"  :file-list="fileLists" :on-change="handleChange" :on-remove="beforeRemove" :auto-upload="false" :on-success="handleSuc" :on-error="handleError" :on-exceed="handleExceed">
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip" slot="tip">只能上传excel文件（accept="xlsx,xls"），且不超过10M</div>
      </el-upload>
      <span slot="footer" class="dialog-footer">
        <el-button @click="beforeClose">取 消</el-button>
        <el-button type="primary" @click="submit()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'FileUpload',
  props: {
    visible: {//对话框的开关
      type: Boolean,
      default: false
    },
    action: {//后台地址
      type: String,
      default: ''
    },
    data: {//请求的额外参数
      type: Object
    }
  },
  data() {
    return {
      title: '上传',
      headers: {
        'X-Requested-With': 'XMLHttpRequest'
      },
      fileLists: [],
      loading: false
    }
  },
  methods: {
    //文件状态改变时的钩子，添加文件、上传成功和上传失败时都会被调用
    handleChange(files, fileList) {
      //获取最后一次得到的文件
      const aa = fileList[fileList.length - 1]
      //覆盖上一次的文件
      this.fileLists = []
      this.fileLists.push(aa)
    },
    //文件超出个数限制时的钩子
    handleExceed(files, fileList) {
      this.$notify.warning({
        title: '提示',
        message: '上传文件已存在,替换请先移除！',
        duration: 2000
      })
    },
    //因为el-upload中已经设置了accept的文件格式，:auto-upload="false" 将自动上传的功能关闭，所以下面的方法省略……
    //上传文件之前的钩子，参数为上传的文件，若返回 false 或者返回 Promise 且被 reject，则停止上传
    handleBeforeUpoad(file) {
      var msg = file.name.substring(file.name.lastIndexOf('.') + 1)
      //判断文件格式
      if (!(msg === 'xls' || msg === 'xlsx')) {
        this.$refs.fileUpload.clearFiles()
        this.$notify.error({
          title: '错误',
          message: '上传文件只能是 xls、xlsx格式!',
          duration: 2000
        })
        return
      }
      //判断文件大小
      if (!(file.size / 1024 / 1024 < 10)) {
        // this.$refs.fileUpload.clearFiles()
        this.$notify.error({
          title: '错误',
          message: '上传文件大小不能超过 10MB!',
          duration: 2000
        })
        return
      }

      // return true
    },
    beforeRemove(file, fileList) {
      //清空已上传的文件列表（该方法不支持在 before-upload 中调用）
      this.$refs.fileUpload.clearFiles()
      this.fileLists = []
    },
    submit() {
   //判断上传文件，没有给出友好提示
      if (!this.fileLists || !this.fileLists.length) {
        this.$notify.error({
          title: '错误',
          message: '请选择上传的文件',
          duration: 2000
        })
        return
      }

      // 再次上传
      this.fileLists[0].status = 'ready'
      this.$refs.fileUpload.submit()
    },
    //文件上传成功时的钩子
    handleSuc(response, file, fileList) {
      //上传成功处理
      if (response.code === 0) {
        this.$notify({
          title: '成功',
          message: '上传成功',
          type: 'success',
          duration: 2000
        })
        this.beforeClose()
        //上传完成以后，调用父组件的方法进行刷新页面操作
        this.$emit('refresh-table', '')
      } else {
        //上传失败处理
        this.$notify.error({
          title: '失败',
          message: response.msg,
          duration: 3000
        })
      }
    },
    handleError(err, file, fileList) {
      //上传失败处理
      this.$notify.error({
        title: '失败',
        message: err,
        duration: 3000
      })
    },
    beforeClose() {
      this.$emit('update:visible', false) // 直接修改父组件的属性
      this.$refs.fileUpload.clearFiles()
    }
  }
}
</script>
