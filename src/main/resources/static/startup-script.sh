#!/bin/sh

URL="http://localhost:51234/tpaTitreRec/TouteLesFactures"

if command -v xdg-open > /dev/null; then
  xdg-open "$URL" &
elif command -v open > /dev/null; then
  open "$URL" &
elif command -v start > /dev/null; then
  start "$URL"
else
  echo "Impossible d'ouvrir le lien dans le navigateur. Veuillez le faire manuellement en ouvrant :"
  echo "$URL"
fi
