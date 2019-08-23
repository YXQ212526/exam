[TOC]
#需求

##前提条件：

1. github创建仓库
2. 使用maven搭建springboot项目，springboot版本指定为2.1.6.RELEASE
3. 创建充值表，字段要求为：id，用户id，地址，币种，txhash，金额，高度，确认次数，状态（初始创建-> 已上账，6次确认上账）时间，版本号（币种 + txHash为联合唯一索引，id为主键自增）；
4. 创建用户表：id,username,phone,state（启用，停用），时间等
5. 创建币种表：ID，currency代码，state（启用，停用）
6. 创建用户和地址绑定表：id，user_id, address，state

##功能要求：
###基本要求：

1. - [x] 通过rest接口充值，数据落入充值表（充值参数位 币种，txhash，地址，金额，高度，确认次数等）
2. - [x] 通过rest接口进行充值确认，更改充值表的确认次数（充值参数位 币种，txhash，地址，金额，高度，确认次数等）
3. - [x]  rest接口查询所有币种
4. - [x] rest接口查询指定用户的地址（参数位 用户id）
5. - [x] rest接口查询指定用户的充值记录参数位 用户id）
6. - [x] 用junit完成单元测试，可以连接本地的mysql数据库做测试。

###加分项：

1. - [x] 币种有缓存，可以使用hashmap，guava，redis。（难度逐渐升级，难度越高加分越多）
2. - [x] 自行设计用户账户表，将充值记录的amount加总到用户账户余额上。
3. - [x] 使用h2做单元测试。
4. 使用mock做单元测试。
#设计
##表设计
1. 币种表
```sql
create table `currency`(
`id` bigint unsigned auto_increment  not null,
`currency_id` bigint not null ,
 `currency_name` varchar(20) not null,
`state`  tinyint(3) default 0 not null,
primary key(`id`),
unique key(`currency_id`)
);
```
2. 用户表
```sql
create table  `user`(
`id` bigint unsigned auto_increment  not null,
`username` varchar(10) not null ,
`phone`  varchar(11)  not null,
`state`  tinyint(3) default 0 not null,
`create_at` bigint ,
primary key(`id`),
unique key(`username`)
);
```
3.  用户地址绑定表
```sql
create table `bind`(
`id` bigint unsigned auto_increment not null,
`user_id` bigint unsigned  not null ,
`address`  varchar(50)  not null,
`state`  tinyint(3) default 0 not null,
primary key(`id`)
);
```
4. 充值表
```sql
create table `deposit`(
`id` bigint unsigned auto_increment not null,
`user_id` bigint unsigned  not null ,
`address`  varchar(50)  not null,
`currency_name` varchar(20) not null,
`txhash` bigint(20) not null,
`amount`  decimal(36,18) unsigned NOT NULL,
`height` bigint not null,
`confirm` int not null,
`state`  tinyint(3) default 0 not null,
`create_at` bigint,
`version` bigint(20) unsigned NOT NULL DEFAULT '0',
primary key(`id`),
unique key(`currency_id`,`txhash`)
);
```
5. 账户表
```sql
create table account(
`id` bigint unsigned auto_increment not null,
`user_id` bigint unsigned  not null ,
`currency_name` varchar(20) not null,
`address`  varchar(50)  not null,
`balance` decimal(36,18) unsigned NOT NULL,
`state`  tinyint(3) default 0 not null,
primary key(`id`),
unique key(`user_id`,`currency_name`)
);
```

##基本逻辑
1. 添加币种信息
2. 创建用户，得到userID
3. 开币种账户，得到充值地址
4. 绑定地址
5. 充值  
   （1）充值
   （2）更改确认次数
   （3）次数为6，对应地址余额增加 

##API
###用户信息
1. 增加用户信息
> *POST   /user/create* 

| 参数                                                     |类型| 返回值     |
| -------------------------------------------------------- |--| ---------- |
| UserParam {<br>   String name;<br>   String phone; <br>} | UserParam|MyResponse |
返回示例：
```
{
"data": "SUCCESS"
}
```
2. 通过用户名获取用户信息
> *GET   /user/select/name* 

| 参数 |类型| 返回值     |
| ---- |--| ---------- |
| id   |Long|MyResponse |
返回示例：
```
{
  "data": {
    "id": 10,
    "name": "xueqi",
    "phone": "188",
    "state": 0,
    "createAt": 1566440026790
  }
}
```
- state：0启用，1停用
3. 通过ID获取用户信息
> *GET   /user/select/id* 

| 参数 |类型| 返回值     |
| ---- |--| ---------- |
| name | String|MyResponse |
返回示例：

```
{
  "data": {
    "id": 10,
    "name": "xueqi",
    "phone": "188",
    "state": 0,
    "createAt": 1566440026790
  }
}
```
- state：0启用，1停用
###账户信息
1. 开户
> *POST  /account/create*

- 开户前判断传入币种是否支持
| 参数                                                         |类型 |返回值     |
| ------------------------------------------------------------ |-- |---------- |
| OpenAccountParam {<br>    Long userId; <br>   String currencyName; <br>} |OpenAccountParam |MyResponse |
返回示例：
```
{
"data": "SUCCESS"
}
```
2. 查询指定账户信息
> *GET  /account/get*
- 用系统时间模拟地址
| 参数 |类型| 返回值     |
| ---- |--| ---------- |
| userId,currencyName | Long,String|MyResponse |
返回实例：
```
{
  "data": {
    "id": 2,
    "userId": 2,
    "currencyName": "fairy",
    "balance": 0,
    "address": "1566479864707",
    "state": 0
  }
}
```
- state：0正常，1冻结
3. 查询所有账户信息
> *GET  /account/get*
- 用系统时间模拟地址
| 参数 |类型| 返回值     |
| ---- |--| ---------- |
| userId| Long|MyResponse |
返回实例：
```
{
  "data": {
    "id": 2,
    "userId": 2,
    "currencyName": "fairy",
    "balance": 0,
    "address": "1566479864707",
    "state": 0
  }
}
```
- state：0正常，1冻结
###用户绑定地址信息
1. 添加用户绑定地址
> *POST  /address/insert*  
- 绑定之前判断用户表中是否有该用户
- 判断地址是否正确

| 参数                                                         | 类型|返回值     |
| ------------------------------------------------------------ | --|---------- |
| UserAddress { <br>   Long userId;<br>   String address;<br>   int state;<br>} |UserAddress |MyResponse |

返回示例：
```
{
  "data": "SUCCESS"
}
```

2. 通过用户名获取绑定地址
> *GET  /address/get*  
- 获取前判断用户表中是否有该用户


| 参数   | 类型|返回值     |
| ------ |-- |---------- |
| userId | Long|MyResponse |

返回示例：
```
{
  "data": [
    {
      "userId": 10,
      "address": "zhaoshang",
      "state": 0
    },
    {
      "userId": 10,
      "address": "jianshe",
      "state": 0
    }
  ]
}
```
- state：0正常，1冻结
###币种信息
1. 添加币种
> *POST  /currency/insert*

| 参数                                                         | 类型|返回值     |
| ------------------------------------------------------------ | --|---------- |
| Currency {<br>  Long currencyId;<br>  int state;  <br>  String name;<br> } |Currency |MyResponse |
返回示例：
```
{
  "data": "SUCCESS"
}
```

2. 获取币种列表
> *GET  /currency/list*

| 参数 | 类型|返回值     |
| ---- |--| ---------- |
| 无   | 无|MyResponse |
返回示例：
```
{
  "data": [
    {
      "currencyId": 1,
      "state": 0,
      "name": "btc"
    },
    {
      "currencyId": 2,
      "state": 0,
      "name": "eth"
    },
    {
      "currencyId": 112,
      "state": 0,
      "name": "er"
    },
    {
      "currencyId": 125,
      "state": 0,
      "name": "fairy"
    }
  ]
}
```
- state：0启用，1停用
###充值
1. 创建充值订单
> *POST  /deposit/create*

- 充值前判断

  - 是否存在账户

  - 传入充值地址是否正确
  - 用户的充值地址是否已绑定


| 参数                                                         | 类型|返回值     |
| ------------------------------------------------------------ |-- |---------- |
| DepositParam {<br>  Long userId; <br>  String currencyName;<br>  Long txHash; <br>  String address; <br>  BigDecimal amount;<br>  Long height;<br>  int confirm;<br> } |DepositParam |MyResponse |
返回示例：
```
{
  "data": "SUCCESS"
}

```


2. 更改上账次数
> *GET  /deposit/confirm*

- 更改前判断是否存在该充值记录
- 上账次数更新为6，对应的地址余额增加

| 参数                                                         | 类型|返回值     |
| ------------------------------------------------------------ |-- |---------- |
| DepositParam {<br/>  Long userId; <br/>  String currencyName;<br/>  Long txHash; <br/>  String address; <br/>  BigDecimal amount;<br/>  Long height;<br/>  int confirm;<br/> } |DepositParam| MyResponse |

返回示例：
```
{
  "data": "SUCCESS"
}

```

3. 查询充值订单
> *GET  /deposit/get*

| 参数   | 类型|返回值     |
| ------ | --|---------- |
| userId | Long|MyResponse |
返回示例：
```
{
  "data": [
    {
      "id": 7,
      "userId": 0,
      "currencyName": "btc",
      "txHash": 88,
      "address": "string",
      "amount": 0,
      "height": 0,
      "confirm": 6,
      "state": 2,
      "createAt": null
    },
    {
      "id": 8,
      "userId": 0,
      "currencyName": "btc",
      "txHash": 8,
      "address": "string",
      "amount": 0,
      "height": 0,
      "confirm": 0,
      "state": 1,
      "createAt": null
    }
  ]
}
```
- state：0初始创建，1等待中，2已上账
- confirm: 等于6上账
##返回不成功信息对应表

| 返回值             | 原因             |
| ------------------ | ---------------- |
| PARAM_NULL         | 传入参数为空     |
| STATE_ERROR        | 状态不正确       |
| NO_USER            | 没有该用户       |
| NO_ADDRESS         | 用户未绑定该地址 |
| UNSUPPORT_CURRENCY | 不支持该币种     |
| NO_DEPOSIT         | 没有该充值记录   |
| CONFIRM_ERROR      | 确认次数不正确   |
| NO_ACCOUNT         | 没有该账户       |
| NOT_BIND_ACCOUNT   | 没有绑定该账户   |
| ADDRESS_ERROR      | 地址不正确       |

##额外说明
- 所有的插入操作均对duplicateKey进行了处理

- 币种简单使用Redis缓存，设置1min过期

  

