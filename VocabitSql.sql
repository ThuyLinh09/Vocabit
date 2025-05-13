-- create database learning_english;
-- use learning_english;
-- Thêm các câu hỏi vào bảng ImageToTextQuestion

INSERT INTO image_to_text_question (id, unit, image_url, correct_option) 
VALUES 
(1, 1, 'https://veemart.scarpercrew.com/storage/fruits-and-veg/apple-158989157.jpg', 'Apple'),
(2, 1, 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/9b/Cavendish_Banana_DS.jpg/330px-Cavendish_Banana_DS.jpg', 'Banana'),
(3, 1, 'https://www.newdirections.com.au/WebRoot/Store/Shops/newdirections/61BB/DCE8/1519/374F/48E6/951C/ADFC/FC9D/CO2.100COCONUT-100-g-Coconut-CO2-Oil-L-20211217_ml.jpg', 'Coconut'),
(4, 1, 'https://www.fervalle.com/wp-content/uploads/2022/07/transparent-orange-apple5eacfeae85ac29.7815306015883956945475-600x600.png', 'Orange'),
(5, 1, 'https://c02.purpledshub.com/uploads/sites/41/2023/09/GettyImages_154514873.jpg', 'Strawberry'),
(6, 1, 'https://australiangrapes.com.au/wp-content/uploads/2020/10/AutumnCrisp-768x957.jpg', 'Grape'),
(7, 1, 'https://upload.wikimedia.org/wikipedia/commons/d/d3/Kiwi_aka.jpg', 'Kiwi'),
(8, 1, 'http://www.plantgrower.org/uploads/6/5/5/4/65545169/published/watermelon2.jpg', 'Watermelon'),
(9, 1, 'https://www.healthxchange.sg/sites/hexassets/Assets/food-nutrition/pineapple-health-benefits-and-ways-to-enjoy.jpg', 'Pineapple'),
(10,1, 'https://traicayhoabien.com/wp-content/uploads/2023/04/CHERRY-KG-1-768x768.webp', 'Cherry'),
(11, 1, 'https://upload.wikimedia.org/wikipedia/commons/e/e4/Lemon.jpg', 'Lemon'),
(12,1, 'https://blog.sakura.co/wp-content/uploads/2022/03/shutterstock_675217411-1.png', 'Peach'),
(13,1, 'https://food.fnr.sndimg.com/content/dam/images/food/fullset/2012/11/27/0/HEW_Pears_s4x3.jpg', 'Pear'),
(14, 1, 'https://dictionary.cambridge.org/vi/images/thumb/plum_noun_002_28096.jpg', 'Plum'),
(15,1, 'https://upload.wikimedia.org/wikipedia/commons/thumb/f/fb/Carabao_mangoes_%28Philippines%29.jpg/330px-Carabao_mangoes_%28Philippines%29.jpg', 'Mango'),

(16, 2, 'https://ontariospca.ca/wp-content/uploads/2024/09/EmilyJeanPhotography-0016-scaled.jpg', 'Dog'),
(17, 2, 'https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Cat_August_2010-4.jpg/330px-Cat_August_2010-4.jpg', 'Cat'),
(18, 2, 'https://cdn.britannica.com/02/152302-050-1A984FCB/African-savanna-elephant.jpg', 'Elephant'),
(19, 2, 'https://res.cloudinary.com/jerrick/image/upload/d_642250b563292b35f27461a7.png,f_jpg,fl_progressive,q_auto,w_1024/65346947755e56001d2510f5.jpg', 'Lion'),
(20, 2, 'https://files.worldwildlife.org/wwfcmsprod/images/Tiger_resting_Bandhavgarh_National_Park_India/hero_full/77ic6i4qdj_Medium_WW226365.jpg', 'Tiger'),
(21, 2, 'https://giraffeworlds.com/wp-content/uploads/habitat_giraffe.jpg', 'Giraffe'),
(22,2 , 'https://upload.wikimedia.org/wikipedia/commons/4/43/Bonnet_macaque_%28Macaca_radiata%29_Photograph_By_Shantanu_Kuveskar.jpg', 'Monkey'),
(23, 2, 'https://www.treehugger.com/thmb/mIDnBoZOKmqQ74EHwi-QDbQBeRM=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/GettyImages-1043597638-49acd69677d7442588c1d8930d298a59.jpg', 'Zebra'),
(24, 2, 'https://upload.wikimedia.org/wikipedia/commons/thumb/0/0d/Kangaroo_and_joey03.jpg/500px-Kangaroo_and_joey03.jpg', 'Kangaroo'),
(25, 2, 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/2010-kodiak-bear-1.jpg/500px-2010-kodiak-bear-1.jpg', 'Bear'),
(26, 2, 'https://www.theinsuranceemporium.co.uk/blog/wp-content/uploads/2023/09/image-10.png', 'Horse'),
(27, 2, 'https://cms.mountpleasant.com.sg/s3media/styles/max_2600x2600/public/2024-02/rabbit-exercise.jpg', 'Rabbit'),
(28, 2, 'https://www.msa.vic.gov.au/__data/assets/image/0010/601003/varieties/image_gallery_large.png', 'Frog'),
(29, 2, 'https://cdn.britannica.com/55/174255-050-526314B6/brown-Guernsey-cow.jpg', 'Cow'),
(30, 2, 'https://eu-images.contentstack.com/v3/assets/bltdd43779342bd9107/blt662268f193621fb4/6393064fcc9a6b25070803fb/sheep-animals-idalGettyImages0702F2-3518_0_0.jpg', 'Sheep');

-- Thêm các đáp án vào bảng image_to_text_options
INSERT INTO image_to_text_options (question_id, options)
VALUES
(1, 'Apple'), (1, 'Banana'), (1, 'Coconut'), (1, 'Orange'),
(2, 'Apple'), (2, 'Banana'), (2, 'Coconut'), (2, 'Orange'),
(3, 'Apple'), (3, 'Banana'), (3, 'Coconut'), (3, 'Strawberry'),
(4, 'Apple'), (4, 'Banana'), (4, 'Orange'), (4, 'Watermelon'),
(5, 'Apple'), (5, 'Banana'), (5, 'Coconut'), (5, 'Strawberry'),
(6, 'Apple'), (6, 'Grape'), (6, 'Coconut'), (6, 'Strawberry'),
(7, 'Apple'), (7, 'Banana'), (7, 'Kiwi'), (7, 'Strawberry'),
(8, 'Apple'), (8, 'Banana'), (8, 'Orange'), (8, 'Watermelon'),
(9, 'Pineapple'), (9, 'Apple'), (9, 'Banana'), (9, 'Cherry'),
(10, 'Pineapple'), (10, 'Apple'), (10, 'Banana'), (10, 'Cherry'),
(11, 'Peach'), (11, 'Lemon'), (11, 'Orange'), (11, 'Strawberry'),
(12, 'Peach'), (12, 'Pear'), (12, 'Plum'), (12, 'Apple'),
(13, 'Peach'), (13, 'Pear'), (13, 'Plum'), (13, 'Apple'),
(14, 'Peach'), (14, 'Pear'), (14, 'Plum'), (14, 'Apple'),
(15, 'Mango'), (15, 'Pineapple'), (15, 'Banana'), (15, 'Coconut'),

(16, 'Dog'), (16, 'Cat'), (16, 'Elephant'), (16, 'Lion'),
(17, 'Dog'), (17, 'Cat'), (17, 'Elephant'), (17, 'Lion'),
(18, 'Elephant'), (18, 'Dog'), (18, 'Cat'), (18, 'Tiger'),
(19, 'Lion'), (19, 'Bear'), (19, 'Tiger'), (19, 'Monkey'),
(20, 'Tiger'), (20, 'Lion'), (20, 'Elephant'), (20, 'Cat'),
(21, 'Giraffe'), (21, 'Elephant'), (21, 'Zebra'), (21, 'Lion'),
(22, 'Monkey'), (22, 'Bear'), (22, 'Rabbit'), (22, 'Horse'),
(23, 'Zebra'), (23, 'Lion'), (23, 'Tiger'), (23, 'Cat'),
(24, 'Kangaroo'), (24, 'Bear'), (24, 'Monkey'), (24, 'Rabbit'),
(25, 'Bear'), (25, 'Elephant'), (25, 'Lion'), (25, 'Monkey'),
(26, 'Horse'), (26, 'Zebra'), (26, 'Tiger'), (26, 'Dog'),
(27, 'Rabbit'), (27, 'Monkey'), (27, 'Bear'), (27, 'Horse'),
(28, 'Frog'), (28, 'Rabbit'), (28, 'Elephant'), (28, 'Cat'),
(29, 'Cow'), (29, 'Sheep'), (29, 'Rabbit'), (29, 'Lion'),
(30, 'Sheep'), (30, 'Cow'), (30, 'Lion'), (30, 'Bear');

INSERT INTO fill_in_blank_question (id, unit, sentence, correct_option) VALUES
(1, 1, 'He ___ to school every day.', 'goes'),
(2, 1, 'They are playing ___ the park.', 'in'),
(3, 1, 'I have a ___ dog.', 'big'),
(4, 1, 'She is ___ than her sister.', 'taller'),
(5, 1, 'We ___ football every weekend.', 'play'),
(6, 1, 'She ___ like bananas.', 'doesn’t'),
(7, 1, 'We ___ going to the zoo tomorrow.', 'are'),
(8, 1, 'I ___ a sandwich for lunch.', 'ate'),
(9, 1, 'This apple is ___ than that one.', 'bigger'),
(10, 1, 'There are ___ people in the room.', 'many'),
(11, 1, 'My mother ___ very kind.', 'is'),
(12, 1, 'He ___ to music in his free time.', 'listens'),
(13, 1, 'I ___ my homework last night.', 'did'),
(14, 1, 'Can you ___ me the book?', 'give'),
(15, 1, 'The sun ___ in the east.', 'rises');

INSERT INTO fill_in_blank_options (question_id, options) VALUES
(1, 'go'), (1, 'goes'), (1, 'going'), (1, 'gone'),
(2, 'at'), (2, 'on'), (2, 'in'), (2, 'under'),
(3, 'small'), (3, 'big'), (3, 'short'), (3, 'tall'),
(4, 'tall'), (4, 'more tall'), (4, 'taller'), (4, 'tallest'),
(5, 'play'), (5, 'plays'), (5, 'played'), (5, 'playing'),
(6, 'don’t'), (6, 'doesn’t'), (6, 'isn’t'), (6, 'wasn’t'),
(7, 'is'), (7, 'are'), (7, 'were'), (7, 'am'),
(8, 'eat'), (8, 'ate'), (8, 'eating'), (8, 'eaten'),
(9, 'bigger'), (9, 'biggest'), (9, 'more big'), (9, 'big'),
(10, 'much'), (10, 'many'), (10, 'lot'), (10, 'a few'),
(11, 'are'), (11, 'is'), (11, 'am'), (11, 'be'),
(12, 'listens'), (12, 'listen'), (12, 'listened'), (12, 'listening'),
(13, 'do'), (13, 'does'), (13, 'did'), (13, 'done'),
(14, 'give'), (14, 'take'), (14, 'bring'), (14, 'show'),
(15, 'rises'), (15, 'sets'), (15, 'raise'), (15, 'lift');

INSERT INTO extra_letter_question (id, unit, incorrect_word, correct_word) VALUES
(1, 1, 'aopple', 'apple'),
(2, 1, 'bananna', 'banana'),
(3, 1, 'cocconut', 'coconut'),
(4, 1, 'orangge', 'orange'),
(5, 1, 'strawbberry', 'strawberry'),
(6, 1, 'grapess', 'grapes'),
(7, 1, 'kiwii', 'kiwi'),
(8, 1, 'watermelonl', 'watermelon'),
(9, 1, 'pineapplle', 'pineapple'),
(10, 1, 'cheery', 'cherry'),
(11, 1, 'lemmon', 'lemon'),
(12, 1, 'peacch', 'peach'),
(13, 1, 'peasr', 'pear'),
(14, 1, 'plumm', 'plum'),
(15, 1, 'maango', 'mango');

-- Thêm câu hỏi vào bảng MatchQuestion
INSERT INTO match_question (id, unit) 
VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(7, 1),
(8, 1),
(9, 1),
(10, 1),
(11, 1),
(12, 1),
(13, 1),
(14, 1),
(15, 1);

-- Thêm lựa chọn tiếng Anh vào bảng match_question_choices_en
INSERT INTO match_question_choices_en (question_id, choices_en)
VALUES
(1, 'Cat'), (1, 'Dog'), (1, 'Tiger'),
(2, 'Apple'), (2, 'Banana'), (2, 'Mango'),
(3, 'Car'), (3, 'Bus'), (3, 'Bike'),
(4, 'Red'), (4, 'Blue'), (4, 'Green'),
(5, 'Chair'), (5, 'Table'), (5, 'Sofa'),
(6, 'Piano'), (6, 'Guitar'), (6, 'Violin'),
(7, 'Sun'), (7, 'Moon'), (7, 'Stars'),
(8, 'King'), (8, 'Queen'), (8, 'Prince'),
(9, 'Teacher'), (9, 'Student'), (9, 'Principal'),
(10, 'Mountain'), (10, 'River'), (10, 'Lake'),
(11, 'Shirt'), (11, 'Pants'), (11, 'Shoes'),
(12, 'Carrot'), (12, 'Potato'), (12, 'Tomato'),
(13, 'Lion'), (13, 'Elephant'), (13, 'Giraffe'),
(14, 'Hot'), (14, 'Cold'), (14, 'Warm'),
(15, 'Rain'), (15, 'Snow'), (15, 'Wind');

-- Thêm lựa chọn tiếng Việt vào bảng match_question_choices_vn
INSERT INTO match_question_choices_vn (question_id, choices_vn)
VALUES
(1, 'Mèo'), (1, 'Chó'), (1, 'Hổ'),
(2, 'Táo'), (2, 'Chuối'), (2, 'Xoài'),
(3, 'Ô tô'), (3, 'Xe bus'), (3, 'Xe đạp'),
(4, 'Đỏ'), (4, 'Xanh'), (4, 'Lục'),
(5, 'Ghế'), (5, 'Bàn'), (5, 'Sofa'),
(6, 'Piano'), (6, 'Đàn guitar'), (6, 'Đàn violin'),
(7, 'Mặt trời'), (7, 'Mặt trăng'), (7, 'Ngôi sao'),
(8, 'Vua'), (8, 'Nữ hoàng'), (8, 'Hoàng tử'),
(9, 'Giáo viên'), (9, 'Học sinh'), (9, 'Hiệu trưởng'),
(10, 'Núi'), (10, 'Sông'), (10, 'Hồ'),
(11, 'Áo sơ mi'), (11, 'Quần'), (11, 'Giày'),
(12, 'Cà rốt'), (12, 'Khoai tây'), (12, 'Cà chua'),
(13, 'Sư tử'), (13, 'Voi'), (13, 'Hươu cao cổ'),
(14, 'Nóng'), (14, 'Lạnh'), (14, 'Ấm'),
(15, 'Mưa'), (15, 'Tuyết'), (15, 'Gió');

-- Thêm các cặp đúng giữa lựa chọn tiếng Anh và tiếng Việt vào bảng match_question_correct_matches
INSERT INTO match_question_correct_matches (question_id, choice_en, choice_vn)
VALUES
(1, 'Cat', 'Mèo'), (1, 'Dog', 'Chó'), (1, 'Tiger', 'Hổ'),
(2, 'Apple', 'Táo'), (2, 'Banana', 'Chuối'), (2, 'Mango', 'Xoài'),
(3, 'Car', 'Ô tô'), (3, 'Bus', 'Xe bus'), (3, 'Bike', 'Xe đạp'),
(4, 'Red', 'Đỏ'), (4, 'Blue', 'Xanh'), (4, 'Green', 'Lục'),
(5, 'Chair', 'Ghế'), (5, 'Table', 'Bàn'), (5, 'Sofa', 'Sofa'),
(6, 'Piano', 'Piano'), (6, 'Guitar', 'Đàn guitar'), (6, 'Violin', 'Đàn violin'),
(7, 'Sun', 'Mặt trời'), (7, 'Moon', 'Mặt trăng'), (7, 'Stars', 'Ngôi sao'),
(8, 'King', 'Vua'), (8, 'Queen', 'Nữ hoàng'), (8, 'Prince', 'Hoàng tử'),
(9, 'Teacher', 'Giáo viên'), (9, 'Student', 'Học sinh'), (9, 'Principal', 'Hiệu trưởng'),
(10, 'Mountain', 'Núi'), (10, 'River', 'Sông'), (10, 'Lake', 'Hồ'),
(11, 'Shirt', 'Áo sơ mi'), (11, 'Pants', 'Quần'), (11, 'Shoes', 'Giày'),
(12, 'Carrot', 'Cà rốt'), (12, 'Potato', 'Khoai tây'), (12, 'Tomato', 'Cà chua'),
(13, 'Lion', 'Sư tử'), (13, 'Elephant', 'Voi'), (13, 'Giraffe', 'Hươu cao cổ'),
(14, 'Hot', 'Nóng'), (14, 'Cold', 'Lạnh'), (14, 'Warm', 'Ấm'),
(15, 'Rain', 'Mưa'), (15, 'Snow', 'Tuyết'), (15, 'Wind', 'Gió');

INSERT INTO practice (id, title, unit, question_type) VALUES
(1, null, 1, 'IMAGE_TO_TEXT'),
(2, null, 1, 'FILL_IN_BLANK'),
(3, null, 1, 'EXTRA_LETTER'),
(4, null, 1, 'MATCHING'),
(5, null, 2, 'IMAGE_TO_TEXT');






