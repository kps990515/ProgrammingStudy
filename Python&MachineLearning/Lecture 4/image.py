from scipy.misc import imread,imsave,imresize

## jpeg파일을 numpy 배열로 변환해서 읽어오기
img = imread('cat.jpg')
print(img.dtype,img.shape)

## 사이즈 조절
img_tinted = img * [1,0.95,0.9]

## img_tinted는 cat 사이즈 조절한 img
img_tinted = imresize(img_tinted,(300,300))

## cat_tinted로 사진저장
imsave('cat_tintied.jpg',img_tinted)

