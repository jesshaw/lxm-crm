# 开发指南

## 简介

## 文档

### 如何在本地运行文档

须确保已安装docker

运行docker容器 -> mkdocs启动服务

```bash
docker run --rm -it -p 8000:8000 -v ${PWD}:/docs ghcr.io/jesshaw/mynotes bash
mkdocs serve --dev-addr=0.0.0.0:8000
```
