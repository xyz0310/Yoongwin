<template>
  <div class="block text-center">
    <el-carousel height="848px" :interval="20000" motion-blur>
      <el-carousel-item v-for="(img, index) in images" :key="index">
        <img :src="img" class="carousel-image" />
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script setup>
import profileImg from '@/assets/photo/1.jpg';
import loginBg from '@/assets/images/login-background.jpg';

const images = [profileImg, loginBg];
</script>

<style scoped>
.demonstration {
  color: var(--el-text-color-secondary);
}

.carousel-image {
  width: 100%;
  height: 100%;
  object-fit: contain;
  display: block;
  margin: auto;
}

.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>