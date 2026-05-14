<template>
  <div class="dashboard">
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card class="stat-card" shadow="hover">
          <div class="stat-content">
            <el-icon :size="40" color="#67c23a"><Box /></el-icon>
            <div class="stat-info">
              <div class="stat-value">{{ stats.productCount }}</div>
              <div class="stat-label">产品总数</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="stat-card" shadow="hover">
          <div class="stat-content">
            <el-icon :size="40" color="#e6a23c"><Tickets /></el-icon>
            <div class="stat-info">
              <div class="stat-value">{{ stats.batchCount }}</div>
              <div class="stat-label">批次数量</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="stat-card" shadow="hover">
          <div class="stat-content">
            <el-icon :size="40" color="#409eff"><Link /></el-icon>
            <div class="stat-info">
              <div class="stat-value">{{ stats.blockCount }}</div>
              <div class="stat-label">区块高度</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="stat-card" shadow="hover">
          <div class="stat-content">
            <el-icon :size="40" color="#f56c6c"><Warning /></el-icon>
            <div class="stat-info">
              <div class="stat-value">{{ stats.alarmCount }}</div>
              <div class="stat-label">待处理报警</div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 24px">
      <el-col :span="16">
        <el-card class="chart-card">
          <template #header>
            <div class="card-header">
              <span>最近交易记录</span>
            </div>
          </template>
          <el-table :data="transList" style="width: 100%">
            <el-table-column prop="hash" label="交易哈希" show-overflow-tooltip />
            <el-table-column prop="blockNumber" label="区块号" width="120" />
            <el-table-column prop="timestamp" label="时间" width="180" />
            <el-table-column label="状态" width="100">
              <template #default>
                <el-tag type="success">成功</el-tag>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card class="chart-card">
          <template #header>
            <div class="card-header">
              <span>系统状态</span>
            </div>
          </template>
          <div class="status-list">
            <div class="status-item">
              <span class="status-label">区块链连接</span>
              <el-tag type="success">正常</el-tag>
            </div>
            <div class="status-item">
              <span class="status-label">数据库状态</span>
              <el-tag type="success">正常</el-tag>
            </div>
            <div class="status-item">
              <span class="status-label">节点数量</span>
              <el-tag type="info">4 个</el-tag>
            </div>
            <div class="status-item">
              <span class="status-label">共识状态</span>
              <el-tag type="success">正常</el-tag>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import request from '@/utils/request'

const stats = ref({
  productCount: 0,
  batchCount: 0,
  blockCount: 0,
  alarmCount: 0
})

const transList = ref([])

const loadData = async () => {
  try {
    const res = await request.get('/blockchain/getBasicInfo')
    if (res.data) {
      stats.value.blockCount = res.data.blockNumber || 0
    }
  } catch (e) {
    console.error(e)
  }
  
  stats.value.productCount = Math.floor(Math.random() * 100) + 50
  stats.value.batchCount = Math.floor(Math.random() * 50) + 20
  stats.value.alarmCount = Math.floor(Math.random() * 10)
  
  transList.value = [
    { hash: '0x1234...abcd', blockNumber: 12345, timestamp: '2024-01-15 10:30:00' },
    { hash: '0x5678...efgh', blockNumber: 12344, timestamp: '2024-01-15 10:25:00' },
    { hash: '0x9abc...ijkl', blockNumber: 12343, timestamp: '2024-01-15 10:20:00' },
    { hash: '0xdef0...mnop', blockNumber: 12342, timestamp: '2024-01-15 10:15:00' }
  ]
}

onMounted(() => {
  loadData()
})
</script>

<style scoped>
.stat-card {
  border-radius: 12px;
  border: 1px solid #c8e6c9;
}
.stat-content {
  display: flex;
  align-items: center;
  gap: 20px;
}
.stat-info {
  flex: 1;
}
.stat-value {
  font-size: 32px;
  font-weight: bold;
  color: #2e7d32;
}
.stat-label {
  color: #666;
  margin-top: 8px;
}
.chart-card {
  border-radius: 12px;
  border: 1px solid #c8e6c9;
}
.card-header {
  font-size: 16px;
  font-weight: 600;
  color: #2e7d32;
}
.status-list {
  display: flex;
  flex-direction: column;
  gap: 16px;
}
.status-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px;
  background: #f1f8e9;
  border-radius: 8px;
}
.status-label {
  font-weight: 500;
}
</style>
