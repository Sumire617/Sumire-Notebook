# PersonalPortfolio

`PersonalPortfolio` 是基于 `BasicHtmlWebsite` 项目开发的一个响应式网页项目，旨在实现对移动设备的适配。

## 项目特点

1. **继承基础功能**  
   `PersonalPortfolio` 项目继承了 `BasicHtmlWebsite` 的所有功能，包括项目展示、工作经验、教育背景和教师评价等模块。

2. **响应式设计**  
   使用 CSS 媒体查询（`@media`）实现了对不同设备（如手机、平板、电脑）的适配：

   - 在移动设备上，内容以单列布局显示，导航栏和侧边栏重新排列。
   - 在电脑设备上，内容以三列布局显示，保持传统桌面端的设计。

3. **优化用户体验**
   - 在移动设备上，导航栏内容居中显示。
   - 调整了字体大小、间距和布局，使内容在不同设备上都能清晰呈现。

## 文件结构

- `PersonalPortfolio.html`  
  主 HTML 文件，定义了网页的结构和内容。

- `PersonalPortfolio.css`  
  样式文件，包含基础样式和响应式设计的媒体查询。

- `icon.png`  
  用于导航栏的图标。

## 如何运行

1. 打开 `PersonalPortfolio.html` 文件即可在浏览器中查看效果。
2. 调整浏览器窗口大小，观察页面在不同设备宽度下的布局变化。

## 对比

- **BasicHtmlWebsite**  
  仅支持固定布局，未针对移动设备优化。
- **PersonalPortfolio**  
  增加了响应式设计，适配移动设备，提升了用户体验。
