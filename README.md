# Block Trace - 农产品区块链溯源系统

基于 **FISCO BCOS** 区块链的农产品全生命周期溯源管理平台，实现从生产、加工、运输到销售各环节的数据上链与可信追溯。

<br />

## 系统架构

```
┌─────────────────────────────────────────────────────┐
│                   前端 (Vue 3)                       │
│   http://localhost:3000                              │
│  ┌─────────┬─────────┬──────────┬─────────┐         │
│  │Dashboard │ Products│  Batch   │ Trace   │         │
│  ├─────────┼─────────┼──────────┼─────────┤         │
│  │Evaluate │ Welfare │ Activity │ Alarm   │         │
│  └─────────┴─────────┴──────────┴─────────┘         │
└──────────────────────┬──────────────────────────────┘
                       │ API (Axios Proxy)
                       ▼
┌─────────────────────────────────────────────────────┐
│               后端 (Spring Boot)                      │
│   http://localhost:8081                              │
│  ┌──────────┬──────────┬───────────┬──────────┐     │
│  │Controller│ Service  │  Mapper   │ Config   │     │
│  └──────────┴──────────┴───────────┴──────────┘     │
│  ┌──────────┐ ┌──────────┐ ┌──────────────────┐     │
│  │Security  │ │MyBatis-Plus│ │FISCO BCOS SDK   │     │
│  └──────────┘ └──────────┘ └──────────────────┘     │
├──────────────┴────────────┴────────────────────────┤
│  MySQL (3306)    Redis (6379)    FISCO BCOS Node   │
│  block_trace      cache/session    blockchain       │
└─────────────────────────────────────────────────────┘
```

## 技术栈

| 层级      | 技术                  | 版本        |
| ------- | ------------------- | --------- |
| **前端**  | Vue 3               | ^3.4.0    |
| <br />  | Vite                | ^5.0.8    |
| <br />  | Element Plus        | ^2.4.4    |
| <br />  | Vue Router          | ^4.2.5    |
| <br />  | Axios               | ^1.6.2    |
| <br />  | Sass                | ^1.69.5   |
| **后端**  | Java / JDK          | 17        |
| <br />  | Spring Boot         | 2.7.18    |
| <br />  | Spring Security     | (随Boot版本) |
| <br />  | MyBatis-Plus        | 3.5.5     |
| <br />  | Druid连接池            | 1.2.20    |
| <br />  | PageHelper          | 1.4.7     |
| <br />  | FastJSON2           | 2.0.43    |
| **区块链** | FISCO BCOS Java SDK | 3.7.0     |
| **数据库** | MySQL               | 8.0+      |
| **缓存**  | Redis               | 6.x       |

## 项目结构

```
agriProductTrace/
├── frontend/                          # 前端工程
│   ├── src/
│   │   ├── views/                    # 页面组件
│   │   │   ├── Dashboard.vue         # 首页仪表盘
│   │   │   ├── Products.vue          # 农产品管理
│   │   │   ├── Batch.vue             # 批次管理
│   │   │   ├── Trace.vue             # 溯源查询
│   │   │   ├── Evaluate.vue          # 评价管理
│   │   │   ├── Welfare.vue           # 福利中心
│   │   │   ├── Activity.vue          # 营销活动
│   │   │   └── Alarm.vue             # 告警记录
│   │   ├── router/index.js           # 路由配置
│   │   ├── utils/request.js          # Axios 封装
│   │   ├── styles/theme.scss         # 主题样式
│   │   ├── App.vue                   # 根组件
│   │   └── main.js                   # 入口文件
│   ├── vite.config.js                # Vite 配置（代理）
│   └── package.json                  # 前端依赖
│
├── src/main/                          # 后端工程
│   ├── java/com/trace/
│   │   ├── TraceApplication.java     # 启动类
│   │   ├── config/                   # 配置层
│   │   │   ├── SecurityConfig.java   # 安全配置
│   │   │   └── FiscoConfig.java      # 区块链SDK配置
│   │   ├── controller/               # 控制器层
│   │   │   ├── TraceProductsController.java
│   │   │   ├── TraceBatchController.java
│   │   │   ├── BlockchainInfoController.java
│   │   │   ├── TraceActivityController.java
│   │   │   ├── TraceWelfareController.java
│   │   │   ├── TraceEvaluateController.java
│   │   │   ├── TraceAlarmController.java
│   │   │   ├── TraceTransController.java
│   │   │   ├── TraceDataStructureController.java
│   │   │   └── TraceProductListController.java
│   │   ├── service/                  # 服务层接口+实现
│   │   ├── mapper/                   # MyBatis Mapper接口
│   │   ├── entity/                   # 数据库实体类
│   │   ├── dto/                      # 数据传输对象
│   │   ├── blockchain/               # 区块链模块
│   │   │   ├── client/               # 合约客户端
│   │   │   ├── contract/             # 智能合约ABI
│   │   │   └── common/               # 公共工具
│   │   ├── common/                   # 公共模块
│   │   │   ├── annotation/           # 自定义注解
│   │   │   ├── core/                 # 核心基类(R/AjaxResult等)
│   │   │   ├── enums/                # 枚举定义
│   │   │   └── utils/                # 工具类
│   │   └── vo/                      # 视图对象
│   └── resources/
│       ├── application.yml           # 主配置文件
│       ├── sql/                      # 数据库脚本
│       │   ├── init.sql              # 初始化脚本
│       │   └── fix_*.sql             # 表结构修复脚本
│       └── mapper/system/            # MyBatis XML映射
│
└── pom.xml                           # Maven依赖配置
```

## 快速开始

### 环境要求

- JDK 17+
- Node.js 16+
- MySQL 8.0+
- Redis 6.x
- Maven 3.6+

### 1. 初始化数据库

```bash
# 创建数据库
mysql -u root -p123456 -e "CREATE DATABASE block_trace DEFAULT CHARSET utf8mb4;"

# 执行初始化脚本
mysql -u root -p123456 block_trace < src/main/resources/sql/init.sql

# 执行表结构修复脚本（确保与Mapper XML一致）
mysql -u root -p123456 block_trace < src/main/resources/sql/fix_all_tables.sql
```

### 2. 启动后端

```bash
# 项目根目录下执行
mvn spring-boot:run
```

后端启动后访问：<http://localhost:8081>

### 3. 启动前端

```bash
cd frontend
npm install
npm run dev
```

前端启动后访问：<http://localhost:3000>

### 4. 访问系统

| 地址                            | 说明                      |
| ----------------------------- | ----------------------- |
| <http://localhost:3000>       | 前端页面                    |
| <http://localhost:8081>       | 后端API                   |
| <http://localhost:8081/druid> | Druid监控台 (admin/123456) |

## API 接口文档

### 农产品管理 `/system/products`

| 方法     | 路径                       | 说明         |
| ------ | ------------------------ | ---------- |
| GET    | `/system/products/list`  | 查询产品列表(分页) |
| GET    | `/system/products/{id}`  | 查询产品详情     |
| POST   | `/system/products`       | 新增产品       |
| PUT    | `/system/products`       | 修改产品       |
| DELETE | `/system/products/{ids}` | 删除产品       |

### 批次管理 `/system/batch`

| 方法  | 路径                   | 说明         |
| --- | -------------------- | ---------- |
| GET | `/system/batch/list` | 查询批次列表(分页) |
| GET | `/system/batch/{id}` | 查询批次详情     |

### 区块链接口 `/blockchain`

| 方法   | 路径                                       | 说明        |
| ---- | ---------------------------------------- | --------- |
| GET  | `/blockchain/getBasicInfo`               | 获取仪表盘统计数据 |
| GET  | `/blockchain/getTraceInfo`               | 根据批次号溯源查询 |
| POST | `/blockchain/uploadProduct`              | 产品数据上链    |
| GET  | `/blockchain/getRank`                    | 产品批次排名    |
| GET  | `/blockchain/getEvaluateList`            | 获取评价列表    |
| GET  | `/blockchain/getWelfareSts`              | 福利统计      |
| GET  | `/blockchain/getActivitySts`             | 活动统计      |
| GET  | `/blockchain/getAlarmSts`                | 告警统计      |
| GET  | `/blockchain/getDataStructure`           | 数据结构统计    |
| GET  | `/blockchain/getTransList`               | 交易流水列表    |
| GET  | `/blockchain/welfare`                    | 领取福利      |
| GET  | `/blockchain/exchange`                   | 积分兑换活动    |
| GET  | `/blockchain/getActivityList`            | 活动列表      |
| GET  | `/blockchain/getWelfareList`             | 福利列表      |
| GET  | `/blockchain/getExchangeHistory`         | 兑换历史      |
| GET  | `/blockchain/integralTrans`              | 积分转让      |
| GET  | `/blockchain/submitEvaluate`             | 提交评价      |
| GET  | `/blockchain/getIntegralBalance`         | 查询积分余额    |
| GET  | `/blockchain/getBlockInfo`               | 区块信息查询    |
| GET  | `/blockchain/getBlockInfoByNumberOrHash` | 交易查询      |

### 请求/响应格式

**统一响应体 R:**

```json
{
  "code": 200,
  "msg": "操作成功",
  "data": { ... }
}
```

**分页响应体 TableDataInfo:**

```json
{
  "code": 200,
  "msg": "查询成功",
  "total": 100,
  "rows": [ ... ]
}
```

## 数据库设计

### 核心业务表

| 表名                   | 说明     | 关键字段                                                   |
| -------------------- | ------ | ------------------------------------------------------ |
| `trace_products`     | 农产品主表  | id, code, name, product\_id, data\_json, node          |
| `trace_batch`        | 批次管理表  | id, batch\_no, product\_id, product\_name, block\_hash |
| `trace_product_list` | 产品列表视图 | id, code, name, check\_status, sale\_status            |
| `trace_record`       | 溯源记录表  | record\_id, product\_id, stage, tx\_hash               |
| `trace_trans`        | 交易流水表  | id, from\_user, to\_user, amount, block\_hash          |

### 区块链相关表

| 表名                       | 说明     |
| ------------------------ | ------ |
| `trace_evaluate`         | 上链评价记录 |
| `trace_welfare`          | 福利积分配置 |
| `trace_activity`         | 营销活动配置 |
| `trace_activity_records` | 活动参与记录 |
| `trace_alarm`            | 告警规则配置 |
| `trace_alaramrecords`    | 告警触发记录 |
| `trace_data_structure`   | 数据结构模板 |

### 系统表

| 表名         | 说明   |
| ---------- | ---- |
| `sys_user` | 用户信息 |

## 功能模块

### 1. Dashboard 仪表盘

- 产品数量、批次数量、活动数量、福利数量、评价数量统计
- 产品批次排名 TOP5
- 福利发放趋势图
- 活动发放/领取对比图
- 告警分布统计

### 2. 产品管理

- 产品 CRUD 操作
- 数据字段动态配置（通过 data\_json JSON格式）
- 产品上链（写入区块链获取交易哈希）
- 告警规则自动检测（新增时触发阈值判断）

### 3. 批次管理

- 批次信息维护
- 批次号溯源查询
- 上链状态追踪

### 4. 溯源查询

- 输入批次号查询完整溯源链
- 返回批次详细信息及区块高度/哈希

### 5. 区块链集成

- **TraceEvaluate 合约**: 评价数据上链
- **TraceRecords 合约**: 溯源记录上链
- **UserAccount 合约**: 用户积分账户管理
- 所有合约操作自动记录 block\_number 和 tx\_hash

### 6. 福利中心

- 福利商品展示
- 积分领取（链上积分操作）
- 积分转让
- 兑换历史

### 7. 营销活动

- 活动发布与管理
- 积分兑换商品
- 兑换记录上链

### 8. 告警系统

- 告警规则配置（阈值/运算符）
- 新增产品时自动检测并生成告警记录
- 告警统计分析

## 配置说明

### application.yml 关键配置

```yaml
server:
  port: 8081                          # 后端端口

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/block_trace
    username: root
    password: '123456'
  redis:
    host: localhost
    port: 6379
    database: 0

mybatis-plus:
  mapper-locations: classpath*:mapper/**/*Mapper.xml
  typeAliasesPackage: com.trace.entity,com.trace.dto
```

### Vite 代理配置 (vite.config.js)

```javascript
proxy: {
  '/api': {
    target: 'http://localhost:8081',  // 代理到后端
    changeOrigin: true,
    rewrite: (path) => path.replace(/^\/api/, '')
  }
}
```

前端请求 `/api/xxx` → 代理到后端 `/xxx`

## 开发注意事项

1. **FISCO BCOS 节点**: 当前为无节点模式运行，区块链相关接口返回模拟数据。如需真实上链，需配置 FISCO BCOS 节点地址并修改 `FiscoConfig.java`
2. **权限控制**: `SecurityConfig` 已开放所有路径的匿名访问，生产环境需配置 JWT 认证
3. **分页参数**: 列表接口使用 PageHelper，默认每页10条，前端传 `page` 和 `size` 参数
4. **数据编码**: 数据库和连接均使用 UTF-8MB4 字符集

## License

MIT
