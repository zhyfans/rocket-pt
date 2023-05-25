# 接口基础



[TOC]

## 总则

所有接口尽量遵循REST规范。接口路径以 `/api` 或 `/api/[version]` 开头

### 请求

token信息后端会自动处理，放入cookie，前端无需关心

##### 分页请求

```
{
  "page": 1,
  "size": 10,
  "keyword": "搜索关键字",
  "prop": "name",
  "sort": "name"
}
```

##### 参数

| 参数名  | 必选 | 类型   | 说明                         |
| :------ | :--- | :----- | ---------------------------- |
| page    | 否   | int    | 页码 从1开始                 |
| size    | 否   | int    | 每页size                     |
| keyword | 否   | string | 模糊搜索关键字 （如有）      |
| prop    | 否   | string | 排序字段                     |
| sort    | 否   | string | 排序类型  可选 "desc", "asc" |



### 返回值

返回值结构如下

```
{
  "code": 0,
  "msg": "string",
  "data": {},
  "page": {
    "total": 1000,
    "page": 1,
    "size": 10
  }
}

```



##### 返回参数详解

| 参数名 | 必选 | 类型         | 说明                       |
| :----- | :--- | :----------- | -------------------------- |
| code   | 是   | int          | 状态码 0 为成功 其他为失败 |
| msg    | 是   | string       | 提示信息                   |
| data   | 否   | object 或 [] | 返回数据                   |
| page   | 否   | object       | 分页信息 （如有）          |
| total  |      | int          | 总记录数                   |
| page   |      | int          | 当前页码                   |
| size   |      | int          | 每页size                   |

 ##### 返回示例
``` 
  {
    "code": 0,
    "msg": "succeed",
    "data": {
      "uid": "1",
      "username": "12154545",
      "name": "吴系挂",
      "groupid": 2 ,
      "reg_time": "1436864169",
      "last_login_time": "0",
    }
  }
```
 ##### 分页示例

``` 
{
  "code": 0,
  "msg": "succeed",
  "data": [
    {
      "uid": "1",
      "username": "12154545",
      "name": "吴系挂",
      "groupid": 2,
      "reg_time": "1436864169",
      "last_login_time": "0",
    }
  ],
  "page": {
    "total": 1000,
    "page": 25,
    "size": 10
  }
}
```

##### 错误示例

```
{
  "code": 404,
  "msg": "User not found",
  "data": null
}
```



#### 常见错误码

| 状态码 | 场景                                                         |
| ------ | ------------------------------------------------------------ |
| 0      | 成功                                                         |
| 202    | 创建成功，通常用在异步操作时，表示请求已接受，但是还没有处理完成 |
| 400    | 参数错误，通常用在表单参数错误                               |
| 401    | 授权错误，通常用在 Token 缺失或失效，注意 401 会触发前端跳转到登录页 |
| 403    | 操作被拒绝，通常发生在权限不足时，注意此时务必带上详细错误信息 |
| 404    | 没有找到对象，通常发生在使用错误的 id 查询详情               |
| 500    | 服务器错误                                                   |