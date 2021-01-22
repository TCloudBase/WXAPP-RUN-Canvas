//index.js
const app = getApp()

Page({
  data: {
    avatarUrl: './user-unlogin.png',
    userInfo: {},
    logged: false,
    takeSession: false,
    requestResult: '',
    inputValue:'https://developers.weixin.qq.com/miniprogram/dev/wxcloud/guide/container/',
    placeholder: '云托管官方文档',
    imagePath:null

  },
  
  bindKeyInput: function(e) {
    this.setData({
      inputValue: e.detail.value
    })
  },
  onLoad: function() {
    var that=this
  wx.cloud.callContainer({
  path: '/container-test1', // 填入容器的访问路径（云托管-服务列表-路径）
  method: 'GET',
  data: {"inputValue":this.data.inputValue},
  success: res => {
    console.log('res: ', res)
    that.setData({    
      imagePath:res.data
  // data 为接口返回的base64字符串  
    })
  },
}),
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              this.setData({
                avatarUrl: res.userInfo.avatarUrl,
                userInfo: res.userInfo
              })
            }
          })
        }
      }
    })
    
  },

  ondraw:function(e){
    wx.canvasPutImageData
  },
  onHello:function(e){
    var that=this
    // 容器调用必填环境id，不能为空
// 确认已经在 onLaunch 中调用过 wx.cloud.init 初始化环境

console.log('inputvalue',this.data.inputValue)
const res =   wx.cloud.callContainer({
  path: '/container-test1', // 填入容器的访问路径（云托管-服务列表-路径）
  method: 'GET',
  data: {"inputValue":this.data.inputValue},
  success: res => {
    console.log('res: ', res)
    that.setData({    
      imagePath:res.data
  // data 为接口返回的base64字符串  
    })
    
  },
})
  }
})
