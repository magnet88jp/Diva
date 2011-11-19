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
  
      .. csv-table:: 
        :header: 項目名, 入力形式, 説明
        :file: ../data/datacolumn-${label}.csv

  メッセージ
    この機能で表示される主なメッセージ（ :ref:`appendixes-majormessage` ）

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

