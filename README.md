cd C:/webDev/webStudy/JSPLastProject
git init
git add .
git commit -m 'commit문자열'
git branch -M main
git remote add origin 'GIT 주소'
git push -u origin main
---------------------------------------- 한번만 수행
수정
git status => branch 확인
git add .
git commit -m 'commit문자열'
git push -u origin main
-----------------------------------------
파일 읽기
git clone "GIT 주소"
branch 변경
    git checkout branch명
    git branch -a // branch 목록 출력

최신 데이터 읽기
git pull origin branch명

git fetch origin branch명 => 원격으로 변경사항 읽기

git merge origin/main => 브런치 병합

충돌
  git add .
  git commit -m "commit다른 명"
  git push origin branch명

git pull --rebase
