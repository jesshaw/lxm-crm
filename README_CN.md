# lxm-crm

Lxm CRM-开源的客户关系管理系统

## 开发

在构建此项目之前，您必须在机器上安装并配置以下依赖项：

1. Node.js：我们使用 Node 来运行开发 Web 服务器并构建项目。
   根据您的系统，您可以从源代码或预打包包安装 Node。

安装 Node 后，您应该能够运行以下命令来安装开发工具。仅当package.json
中的依赖项发生变化时，才需要运行此命令。推荐用`yarn`来管理node包，有时npm会存在内存益出的问题。

```bash
npm install yarn -g
yarn install

npm install
```

我们使用 npm 脚本和 [Webpack][] 作为我们的构建系统。

在两个独立的终端中运行以下命令，以创建一个幸福的开发体验，当硬盘上的文件发生变化时，浏览器会自动刷新。

```bash
# 推荐maven启动后端
mvn spring-boot:run
# 或
./mvnw


# 推荐yarn启动前端
yarn start
# 或
npm start

# 把所有更改合并到最近一次提交，并且不编辑确认
git add . && git commit --amend --no-edit

# 重新发布指定的版本

TAG='v1.1.7' MSG='Add the documentation link and Update the logo' && git push -d origin "${TAG}" && git tag -d "${TAG}" && git tag "${TAG}" -m "${MSG}" && git push origin "${TAG}"

```

Npm 还用于管理此应用程序中使用的 CSS 和 JavaScript 依赖项。您可以通过在[package.json](package.json)
中指定较新版本来升级依赖项。您还可以运行`npm update`和`npm install`管理依赖项。在任何命令上添加`help`标志以了解如何使用它。例如，`npm help update`

该`npm run`命令将列出该项目可供运行的所有脚本。

### 实体生成

基于凯米拉（Chimera）蓝本按需生成业务实体的基本功能。

```bash
jhipster jdl --blueprints chimera --project-version 1.1.7-SNAPSHOT --skip-git --skip-install  crm.jdl

jhipster jdl --blueprints chimera --project-version 1.1.4-SNAPSHOT --skip-git --skip-cache --skip-install  crm.jdl

jhipster jdl --blueprints chimera --project-version 1.1.0-SNAPSHOT --skip-server --skip-git --skip-cache --skip-install  crm.jdl

jhipster jdl --blueprints chimera --project-version 1.1.0-SNAPSHOT --skip-fake-data --skip-db-changelog --skip-git --skip-cache --skip-install  crm.jdl

jhipster jdl --blueprints chimera --project-version 1.1.7-SNAPSHOT --skip-server --skip-git --skip-cache --skip-install --skip-fake-data --skip-db-changelog  crm.jdl

```

_注意_：通过命令行的对话可以记住用户的选择下次不再生成（不生成的文件存放在.yo-resolve）

## Q&A

### `moduleGraph.getParentModule(this)` 返回了 `undefined`，导致无法读取 `buildMeta` 属性

更新 Webpack 和相关插件（如果有必要）

```bash
yarn add webpack webpack-cli --save-dev
```

### 鼠标悬停Tailwind CSS智能提示不能显示

Command Palette(Ctrl+Shift+p) -> Tailwind CSS: Show Output 查看日志排查解决

### 调试时未按预期生成

rm -rf node_modules/generator-jhipster-chimera
