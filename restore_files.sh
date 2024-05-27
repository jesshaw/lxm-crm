#!/bin/bash

# 指定包含文件路径的文件名
input_file=".restorefiles"

# 循环读取文件中的每一行
while IFS= read -r file
do
    # 针对每一行执行 git reset
    git reset -- $file
    # 针对每一行执行 git restore
    git restore -- "$file"
done < "$input_file"

rm -rf "src/main/webapp/content/images/logo-jhipster.png"


# 指定要操作的文件、目标文字和要插入的文本
file="src/main/webapp/app/entities/reducers.ts"
target_text="jhipster-needle-add-reducer-import"
text_to_insert="import dict from 'app/entities/dict/dict.reducer';\nimport authority from 'app/entities/admin/authority/authority.reducer';"

# 使用sed在包含目标文字的行的前一行插入文本并覆盖原文件
sed -i "/$target_text/i\\
$text_to_insert" "$file"
git add -- "$file"

# # 指定要操作的文件、目标文字和要插入的文本
file="src/main/webapp/app/entities/reducers.ts"
target_text="jhipster-needle-add-reducer-combine"
text_to_insert="  dict,\n  authority,"

# 使用sed在包含目标文字的行的前一行插入文本并覆盖原文件
sed -i "/$target_text/i\\
$text_to_insert" "$file"
git add -- "$file"

