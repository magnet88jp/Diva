.. _actions-${label}: 

${title}
===============================================

.. container:: actions
  
  .. rubric:: ${availableMode}
  
  機能概要
    | ${summary?summary.replace("\n", "\n    | "):"なし"}
  
  利用条件
    システム設定（ :ref:`appendixes-systemsetting` ）
  
      .. csv-table:: 
        :header: "変更可否", "設定名", "選択肢／設定値", "デフォルト値", "説明"
        :file: ../data/systemsetting-${label}.csv
  
    ロール設定（ :ref:`appendixes-role` ）
  
      .. csv-table:: 
        :header: "ID", "権限名", "権限パス"
        :file: ../data/function-${label}.csv
  
  
  利用方法（操作方法）
    | ${process?process.replace("\n", "\n    | "):"なし"}
  
    [登録]部分
  
      以下の情報を編集し、[登録]をクリックします。
  
      .. csv-table:: 
        :header: 項目名, 入力形式, 説明
        :file: ../data/datacolumn-${label}.csv

  
  メッセージ
    メッセージの一覧です。

    .. csv-table:: 
      :header: "ID", "コード", "メッセージ", "説明"
      :file: ../data/majormessage-${label}.csv
  
  制限事項
    | ${restriction?restriction.replace("\n", "\n    | "):"なし"}
  
  補足事項
    | ${supplement?supplement.replace("\n", "\n    | "):"なし"}

  関連項目
    なし
  
  よくある質問
    なし

