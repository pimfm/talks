import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { resolve } from 'path'

export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: { '@': resolve(__dirname, 'src') }
  },
  server: {
    port: 5173,
    proxy: {
      '/nl': 'http://localhost:8080',
      '/be': 'http://localhost:8080',
      '/profile': 'http://localhost:8080'
    }
  }
})
