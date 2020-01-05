# web_comic_catalogs

2019年末の冬休みに、モデリングの練習がしたくて作ったものです。

WEB 漫画配信サイトをスクレイピングして, 配信されている漫画の一覧(name, url)を取得する scala アプリです。

漫画の更新情報を取得するアプリの前処理として、漫画のヘッダー情報を取得・永続化を行います。

## 概要

WEB 漫画を配信しているサイトは、大きく分類すると

- 出版社のサイト(少年ジャンプ+, ヤングエースup)
- 出版社のサイトをまとめているサイト(Comic Walker)

と二分され、それぞれが持っている情報は、

- まとめサイト -> 出版社 * n
- 出版社 -> 漫画 * n

の様になります。

出版社の場合は、漫画一覧を取得するだけですが、  
まとめサイトの場合は、出版社一覧を取得してから出版社毎の漫画を取得する必要があります。

## モデリング

- まとめサイト
- 出版社

は、Distributor という概念としてまとめており、  
Distributor に selector, attribute などをまとめた(AnalyzeRule)を渡すと  
下位の情報をスクレイピングできる、という風にモデリングしました。  
ちょっとミスマッチな感じがしていますが、

- まとめサイト
- 出版社
- 漫画

は、保持する情報が大体同じなのと、上下関係を表現したかったので、  
Provider という大きな概念としてまとめています。
