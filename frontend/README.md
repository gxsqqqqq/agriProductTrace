# 区块链溯源系统 - 前端

基于 Vue 3 + Element Plus + Axios 的区块链溯源系统前端界面，采用淡绿色主题配色。

## 功能模块

- **数据概览**：展示系统核心指标和最近交易记录
- **产品管理**：产品信息的增删改查和上链操作
- **批次管理**：产品批次的生成和溯源查询入口
- **溯源查询**：通过批次号查询产品全链路溯源信息
- **营销活动**：积分兑换活动管理
- **福利管理**：积分福利发放记录
- **评价管理**：用户评价的查看和管理
- **报警管理**：异常数据报警处理

## 技术栈

- Vue 3 (Composition API)
- Vue Router 4
- Element Plus
- Axios
- Vite

## 快速开始

### 安装依赖

```bash
cd frontend
npm install
```

### 启动开发服务器

```bash
npm run dev
```

开发服务器默认运行在 http://localhost:3000

### 构建生产版本

```bash
npm run build
```

### 预览生产构建

```bash
npm run preview
```

## 项目结构

```
frontend/
├── src/
│   ├── views/          # 页面组件
│   │   ├── Dashboard.vue
│   │   ├── Products.vue
│   │   ├── Batch.vue
│   │   ├── Trace.vue
│   │   ├── Activity.vue
│   │   ├── Welfare.vue
│   │   ├── Evaluate.vue
│   │   └── Alarm.vue
│   ├── router/         # 路由配置
│   │   └── index.js
│   ├── utils/          # 工具函数
│   │   └── request.js  # Axios 封装
│   ├── styles/         # 样式文件
│   │   └── theme.scss  # 主题样式
│   ├── App.vue         # 根组件
│   └── main.js         # 入口文件
├── index.html
├── vite.config.js
├── package.json
└── README.md
```

## API 代理

开发环境下已配置 API 代理，`/api` 请求会被转发到 `http://localhost:8080`，可在 `vite.config.js` 中修改。

## 主题配色

- 主色：#67c23a (淡绿色)
- 背景：渐变淡绿色
- 其他：遵循 Element Plus 配色规范
