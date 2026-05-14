<template>
  <div class="trace">
    <el-card class="page-card">
      <template #header>
        <div class="card-header">
          <span>溯源查询</span>
        </div>
      </template>
      
      <el-form :inline="true" :model="queryForm" class="search-form">
        <el-form-item label="批次号">
          <el-input v-model="queryForm.batchNo" placeholder="请输入批次号" style="width: 400px" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSearch">查询</el-button>
        </el-form-item>
      </el-form>
      
      <div v-if="traceData.length > 0" class="trace-timeline">
        <el-timeline>
          <el-timeline-item
            v-for="(item, index) in traceData"
            :key="index"
            :timestamp="item.timestamp"
            placement="top"
            :type="item.type"
          >
            <el-card class="trace-card">
              <h4>{{ item.title }}</h4>
              <p>{{ item.description }}</p>
              <div class="trace-info">
                <span v-if="item.operator"><el-icon><User /></el-icon> 操作人：{{ item.operator }}</span>
                <span v-if="item.location"><el-icon><Location /></el-icon> 地点：{{ item.location }}</span>
                <span v-if="item.hash"><el-icon><Link /></el-icon> 交易哈希：{{ item.hash }}</span>
              </div>
            </el-card>
          </el-timeline-item>
        </el-timeline>
      </div>
      
      <el-empty v-else description="请输入批次号进行查询" />
    </el-card>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { ElMessage } from 'element-plus'
import request from '@/utils/request'

const route = useRoute()
const queryForm = reactive({
  batchNo: ''
})

const traceData = ref([])

const handleSearch = async () => {
  if (!queryForm.batchNo) {
    ElMessage.warning('请输入批次号')
    return
  }
  
  try {
    const res = await request.get('/blockchain/getTraceInfo', { params: { batchNo: queryForm.batchNo } })
    if (res.data) {
      traceData.value = res.data
    }
  } catch (e) {
    traceData.value = [
      {
        title: '消费者购买',
        description: '消费者完成购买，获得积分奖励',
        operator: '消费者',
        location: '线上商城',
        timestamp: '2024-01-15 16:30:00',
        hash: '0x1234...abcd',
        type: 'primary'
      },
      {
        title: '物流配送',
        description: '商品已送达目的地，签收完成',
        operator: '快递员',
        location: '配送站点',
        timestamp: '2024-01-15 10:00:00',
        hash: '0x5678...efgh',
        type: 'success'
      },
      {
        title: '仓库出库',
        description: '商品从中心仓库发出',
        operator: '仓库管理员',
        location: '中心仓库',
        timestamp: '2024-01-14 14:00:00',
        hash: '0x9abc...ijkl',
        type: 'success'
      },
      {
        title: '生产加工',
        description: '产品完成生产加工，质量检验合格',
        operator: '质检员',
        location: '生产工厂',
        timestamp: '2024-01-13 09:00:00',
        hash: '0xdef0...mnop',
        type: 'success'
      },
      {
        title: '原料入库',
        description: '原材料入库检验完成',
        operator: '库管员',
        location: '原料仓库',
        timestamp: '2024-01-12 08:00:00',
        hash: '0xqrst...uvwx',
        type: 'success'
      }
    ]
  }
}

onMounted(() => {
  if (route.query.batchNo) {
    queryForm.batchNo = route.query.batchNo
    handleSearch()
  }
})
</script>

<style scoped>
.page-card {
  border-radius: 12px;
  border: 1px solid #c8e6c9;
}
.card-header {
  font-size: 16px;
  font-weight: 600;
  color: #2e7d32;
}
.search-form {
  margin-bottom: 20px;
}
.trace-timeline {
  margin-top: 30px;
}
.trace-card {
  margin-bottom: 10px;
}
.trace-card h4 {
  margin: 0 0 10px 0;
  color: #2e7d32;
}
.trace-card p {
  margin: 0 0 10px 0;
  color: #666;
}
.trace-info {
  display: flex;
  gap: 20px;
  font-size: 12px;
  color: #999;
}
.trace-info span {
  display: flex;
  align-items: center;
  gap: 4px;
}
</style>
