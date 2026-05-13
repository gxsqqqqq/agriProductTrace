# Block Trace - 区块链溯源系统

基于 FISCO BCOS 区块链的溯源管理系统，提供产品溯源、批次管理、营销活动、积分通证等功能。

## 技术栈

- **后端框架**: Spring Boot 2.7.18
- **区块链**: FISCO BCOS Java SDK 3.7.0
- **数据库**: MySQL + MyBatis-Plus
- **安全**: Spring Security + JWT
- **工具库**: Hutool 5.8.25

## 项目结构

```
src/main/java/com/trace/
├── blockchain/          # 区块链交互模块
│   ├── client/         # 区块链客户端
│   ├── contract/       # 智能合约封装
│   └── common/         # 公共组件
├── common/             # 公共模块
│   ├── annotation/     # 自定义注解
│   ├── core/           # 核心类（控制器、实体、分页）
│   ├── enums/          # 枚举类
│   ├── result/         # 统一响应
│   ├── utils/          # 工具类
│   └── xss/           # XSS防护
├── config/             # 配置类
├── controller/         # 控制器层
├── dto/               # 数据传输对象
├── entity/            # 实体类
├── mapper/            # MyBatis Mapper
├── service/           # 服务层
└── vo/                # 视图对象
```

## 功能模块

### 区块链功能
- 区块链区块浏览
- 交易哈希查询
- 产品数据上链
- 积分账户管理

### 业务功能
| 模块 | 说明 |
|------|------|
| 溯源产品 | 产品信息管理 |
| 批次管理 | 批次生成与追溯 |
| 营销活动 | 积分兑换活动 |
| 福利管理 | 积分福利发放 |
| 评价管理 | 用户评价系统 |
| 报警管理 | 异常数据监控 |

## 快速开始

### 环境要求
- JDK 17+
- MySQL 8.0+
- Redis
- FISCO BCOS 节点

### 配置

1. 修改 `src/main/resources/application.yml` 中的数据库配置：
```yaml
spring:
  datasource:
    druid:
      url: jdbc:mysql://localhost:3306/block_trace
      username: root
      password: your_password
```

2. 配置 FISCO 节点信息：
```toml
# src/main/resources/fisco-config.toml
```

### 构建运行

```bash
# 编译项目
mvn clean package -DskipTests

# 运行项目
java -jar target/block-trace-backend-1.0.0.jar
```

服务启动后访问 `http://localhost:8080`

## API 接口

### 区块链信息
- `GET /blockchain/getBlockInfo` - 获取区块信息
- `GET /blockchain/getBasicInfo` - 获取基础统计
- `GET /blockchain/getTransList` - 获取交易列表

### 产品溯源
- `GET /blockchain/getTraceInfo?batchNo=xxx` - 查询批次溯源信息

### 积分管理
- `GET /blockchain/getIntegralBalance?tel=xxx` - 查询积分余额
- `GET /blockchain/integralTrans` - 积分转账

### 活动兑换
- `GET /blockchain/exchange` - 积分兑换
- `GET /blockchain/welfare` - 领取福利

## 数据库初始化

执行 `src/main/resources/sql/init.sql` 初始化数据库表结构。

默认管理员账号：
- 用户名: `admin`
- 密码: `123456` (BCrypt加密)

## License

MIT License
