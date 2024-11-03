# Omnidle 🎮📚

   Omnidle, BTK Hackathon 2024 etkinliği kapsamında Muğla Sıtkı Koçman Üniversitesi Bilgisayar Mühendisliği öğrencileri Ozan Uslan, Rıdvan Barış Özden ve Süleyman Emre Parlak tarafından geliştirilen bir mobil oyundur. Eğitim odaklı bu projede, Google’ın geliştirdiği “Gemini” büyük dil modelinin kullanılması istenmiştir. Projede büyük dil modelinin işlevi, esnek ve üretken bir veritabanı olarak çalışması ve kullanıcı tarafından belirlenen konu üzerine içerik oluşturması olarak belirlenmiştir. Yapay zekanın günümüz koşullarındaki işlevselliği ve sağladığı başarılı çıktılar, uygulamanın sürdürülebilirliği ve işleyişi açısından önemli bir rol oynamıştır.  

   Takımımızın yetkinliği ve tecrübesi sayesinde proje Java dilinde yazılmış olup, Android Studio teknolojisi kullanılmıştır. Kullanıcı dostu arayüzü ve kolay kullanımı ile geniş bir kitleye hitap etmektedir. Yapay zekâ desteği ile içeriklerin oluşturulması, oyuna aktif bir güncellik katmakta ve içeriklerin kullanıcı isteklerine göre uyarlanabilir olmasını sağlamaktadır.

## İçindekiler

- [Nasıl Bir Oyundur?](#nasıl-bir-oyundur-)
- [Eğitim ile İlişkisi](#eğitim-ile-i̇lişkisi-)
- [Nasıl Oynanır?](#nasıl-oynanır-%EF%B8%8F)
- [Kullanıcı Arayüzü](#kullanıcı-taslak-arayüzü-)
- [Oynanış Açıklaması](#oynanış-açıklaması)
- [Oynanış Demo Videosu (Hızlandırılmış)](#oynanış-demo-videosu-hızlandırılmış)


### Nasıl Bir Oyundur? 🎲  

   Omnidle, kullanıcının seçtiği konuya göre oluşturulmuş soruların, A’dan Z’ye doğru sıralanarak sorulduğu bir bilgi yarışması oyunudur. Sorulara verilen cevaplar, “doğru”, “yanlış” veya “pas” olarak işaretlenir. Oyunun sonunda kullanıcıya doğru cevaplar sunularak, kendi cevaplarını analiz etme fırsatı sağlanır.

### Eğitim ile İlişkisi 🎓 

   Omnidle, kullanıcının belirlediği konularda kendini geliştirmesine, bilgi eksiklerini tamamlamasına ve hızlı düşünme yeteneğini artırmasına yardımcı olur. Zamana karşı oynanması, durum kontrolü yapabilme ve zamanı etkin kullanabilme becerilerini de geliştirmekte olup, sınavlara hazırlık gibi eğitimle ilgili birçok ihtiyacı destekler niteliktedir.

### Nasıl Oynanır? 🕹️  

   1) Oyuna giriş yapılır ve “Oyna” tuşuna basılır.  
   2) Kullanıcıdan, cevaplamak istediği soruların oluşturulacağı konu seçilmesi istenir.  
   3) Seçilen konuya göre A’dan Z’ye harflerin yer aldığı oyun ekranı açılır. Ekranda süre başlar ve kullanıcıdan cevaplar vermesi beklenir.  
   4) Kullanıcı soruyu doğru bildiğinde harf yeşile döner ve diğer soruya geçilir.  
   5) Eğer emin değilse “pas” butonuna basarak soruyu geçebilir; bu durumda harf sarıya döner. Kullanıcı daha sonra bu sorulara tekrar dönebilir.  
   6) Yanlış cevap verilirse harf kırmızıya döner ve o soruya tekrar cevap hakkı tanınmaz.  
   7) Tüm sorular “doğru” veya “yanlış” olarak işaretlenmişse oyun süreye bakılmaksızın sona erer. Ancak "pas" olarak işaretlenmiş sorular varsa süre bitene kadar oyun devam eder. Süre dolduğunda veya oyun sona      
   erdiğinde kullanıcı, karşısına çıkan ekranda cevaplarını analiz edebilir.

### Kullanıcı Taslak Arayüzü 👤
![Omnidle](https://github.com/user-attachments/assets/44c21878-ea88-44fe-a470-ae89c1400b7a)

### Oynanış Açıklaması
1. Giriş Ekranı
   Bu ekranda sadece "Oyna" butonu ve oyunun dili bulunmaktadır.
   
   ![Ui1](https://github.com/user-attachments/assets/f037789a-7fd0-468b-820f-c65ed32e5bc6)

2. Oyun Konusu Ekranı
   Bu ekranda öğrenmek istediğimiz konuyu yazıyoruz. Bu herhangi bir şey olabilir. Buradaki örneğimiz "Yazılım". Geri dönmek istersek de "Geri Dön" butonunu kullanabiliriz.
   
   ![Ui2](https://github.com/user-attachments/assets/c3eb4eea-b465-48d0-946c-964986cca392)

   Konuyu onayladıktan sonra yapay zekanın o konuyla her harfin oluşturulacak sorunun cevabının baş harfi olacak şekilde soruları hazırlamasını bekliyoruz.
   
   ![Ui2_2](https://github.com/user-attachments/assets/3ff1eb60-8e02-404a-97cb-4086ea80fc23)

3. Soru Ekranı
   Bu ekranda ekranda çıkan harf, sorunun baş harfini temsil ediyor ve bize yardımcı oluyor. Örneğin bu sorunun cevabı "B" ile başlıyor ve bellek yazıp onaylıyoruz. Bir sonraki soruya geçtiğimizde ise az önceki soruyu doğru yapıp yapmadığımızı renklerden anlıyoruz.
   
   ![Ui3](https://github.com/user-attachments/assets/68c5a4b4-100a-4a37-aafb-7a4a5145e104)

   "C" sorusuna geçtik ve az önceki "B" sorusunun renginin yeşil olmasından doğru yapmış olduğumuzu anladık. Burada cevabı yanlış yapmak için yanlış bir örnek girdik hatta verdiğimiz cevap "C" ile bile başlamıyor.
   
   ![Ui3_2](https://github.com/user-attachments/assets/983cedcb-a460-4dd4-b9eb-0535ec4d5106)

   "Ç" sorusuna geçtik ve az önceki "C" sorusunun renginin kırmızı olmasından yanlış yaptığımızı anladık.
   
   ![Ui3_3](https://github.com/user-attachments/assets/8cd3717a-4ddb-405e-ab6b-59aa8e8efe7e)

   Eğer pasa basarsak ya da cevap vermeden onaylarsak o soru pas olarak kalacaktır ve "Z" sorusu bittikten sonra pas geçtiğimiz tüm sorular tekrardan karşımıza çıkacaktır.
   
   ![Ui3_4](https://github.com/user-attachments/assets/e78f5d82-41c8-46cf-8573-85681e8860f1)

4. Sonuçlar Ekranı
   Bir soruyu istediğimiz kadar pas geçebiliriz ancak oyun süre bittiğinde ya da tüm sorular cevaplandığında (doğru ya da yanlış) bitecektir. Bitiş ekranında ise tüm soruları, soruların cevaplarını ve doğru yapıp yapılmadığını gösteren bilgiler gelecektir.
   
   ![Ui4](https://github.com/user-attachments/assets/7db1f65a-03f5-472f-abc4-2309c7d8c30c)


### Oynanış Demo Videosu (Hızlandırılmış)
   Video çok büyük boyutta olduğu için bu linkten ulaşabilirsiniz: https://drive.google.com/file/d/1sg2wyqMON-ayVPoaav3jTCX-JaSYS9B0/view?usp=drive_link
   
   
