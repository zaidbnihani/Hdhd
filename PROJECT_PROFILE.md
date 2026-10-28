# ملف تعريف المشروع | Project Profile

## العربية

### الغرض
بحسب `README.md` و`docs/BUILDING.md`، هذا المستودع هو NewTube: عميل YouTube غير رسمي للهواتف والأجهزة اللوحية بنظام Android، مبني على وحدات SmartTube مع واجهة لمس ومشغّل Media3. يذكر README تسجيل الدخول برمز، التشغيل في الخلفية، الحفظ دون اتصال والبث إلى SmartTube؛ هذه أوصاف المنتج في README وليست تحققًا مستقلًا من كل ميزة. المشروع موجه للهاتف في حالته الحالية، كما توضح `docs/BUILDING.md` إزالة نكهات التلفاز؛ لذلك لا تعتمد الوصف القديم في `README_AR.md` الذي يصف ZTube/Android TV على أنه وصف حالي.

### المكونات والتدفق الأساسي
- `smarttubetv` هو تطبيق Android الرئيسي؛ مصدر واجهة الهاتف موجود في `smarttubetv/src/stmobile/`، ومصدر Android العام وموارده في `smarttubetv/src/main/`.
- `common` يحتوي على منطق SmartTube المشترك، بما فيه العروض والإعدادات وتكامل الحساب، بحسب `docs/BUILDING.md` و`common/build.gradle`.
- وحدات Gradle الإضافية: `filepicker-lib` و`doubletapplayerview-media3` و`slidableactivity` و`macrobenchmark` و`sabr-media3` (مُدرجة في `settings.gradle`). تعتمد إعدادات المشروع كذلك على الوحدتين الفرعيتين `SharedModules` و`MediaServiceCore`، كما يبين `.gitmodules` و`settings.gradle`.
- مسار المطور الموثق هو استنساخ المستودع مع الوحدات الفرعية، توجيه Gradle إلى Android SDK، ثم بناء نسخة الهاتف عبر Gradle؛ تفاصيل التثبيت موجودة في `docs/BUILDING.md`. يشرح `README.md` تسجيل الدخول من واجهة التطبيق، لكنه لا يوثق تفاصيل داخلية لتنفيذ هذا التدفق.

### خريطة موجزة
```text
.github/workflows/   إجراءات CI وإصدارات ومهام آلية
common/              منطق SmartTube المشترك وموارده
smarttubetv/         تطبيق Android؛ src/stmobile لواجهة الهاتف، وsrc/main للمصادر العامة
filepicker-lib/      وحدة منتقي الملفات
sabr-media3/         وحدة SABR المبنية على Media3
 doubletapplayerview-media3/ وslidableactivity/ وmacrobenchmark/ وحدات مساعدة
 docs/BUILDING.md    متطلبات البناء وأوامر البناء والتثبيت
 docs/mobile-port/   حالة النقل إلى الهاتف وملاحظات هندسية للاعب والشبكة
 docs/releases/      ملاحظات الإصدارات
 tools/              أدوات مساعدة واختبارات Python مرتبطة بها
 README.md           وصف المشروع والميزات والتنزيل
 README_AR.md        ملف عربي موجود، لكن محتواه يصف ZTube/Android TV ويتعارض مع ملفات المشروع الحالية
 settings.gradle     تعريف وحدات Gradle وإعداد مسارات الوحدات الفرعية
 build.gradle        إصدار Android Gradle Plugin والمستودعات وحل التبعيات
 gradle/wrapper/     إصدار Gradle الذي يستخدمه wrapper
```

### الملفات المحورية
- `settings.gradle`: يضم وحدات البناء ويبحث عن `SharedModules` و`MediaServiceCore` داخل الشجرة أو بجوارها.
- `smarttubetv/build.gradle` و`common/build.gradle`: إعدادات التطبيق والمكتبة والتبعيات وإعدادات الاختبارات.
- `gradle/wrapper/gradle-wrapper.properties`: Gradle Wrapper 9.6.1.
- `docs/BUILDING.md`: متطلبات وأوامر البناء والتثبيت المعتمدة في التوثيق.
- `.github/workflows/CI.yml`: سير عمل GitHub Actions للبناء والتحقق.
- `docs/mobile-port/STATUS.md` و`docs/mobile-port/HANDOFF.md`: الحالة والسياق الهندسي لمسار الهاتف واللاعب والشبكة؛ يوصي دليل البناء بقراءة HANDOFF قبل تعديل هذين المجالين.

### التقنية والإصدارات والإعداد
- Android/Gradle: Java 17، Android Gradle Plugin 9.2.1 في `build.gradle`، وGradle 9.6.1 في wrapper. يطلب `docs/BUILDING.md` Android SDK 37؛ ويقرأ `smarttubetv/build.gradle` قيم SDK من ثوابت الوحدة المشتركة.
- التطبيق يعتمد على AndroidX وMaterial Components وMedia3، إضافة إلى الوحدات المشتركة من SmartTube، كما يظهر في `smarttubetv/build.gradle`. يحدد الملف إصدار التطبيق 1.15.0 ونشاط الحزمة `io.github.aleixrodriala.arc` في إعداد نكهة الهاتف، والحد الأدنى API 24. لا تعرض بعض إصدارات/نكهات المصدر إعدادات مختلفة؛ لا تفترض أن كل النكهات تستخدم هذه القيم.
- الترخيص المعلن في `LICENSE` هو MIT.
- إعداد محلي موثق: خاصية `sdk.dir` في `local.properties`. قد يتطلب بناء الإصدار الموزع ملف `keystore.properties` الخاص، وفق `docs/BUILDING.md`؛ لا تضع قيم أسرار أو مفاتيح في ملف التوثيق أو المستودع.
- أسماء متغيرات/أسرار الإعداد المشار إليها في الملفات: `NEWTUBE_BENCH_DIR` و`NEWTUBE_BENCH_LABEL` (اختبارات CPU benchmark)، و`SIGNING_KEY` و`KEY_STORE_PASSWORD` و`ALIAS` و`KEY_PASSWORD` و`VIRUS_TOTAL_API_KEY` (سير CI). لم تُدرج أي قيم.

### البناء والاختبارات وCI
- يتطلب البناء الموثق JDK 17 وAndroid SDK 37 واستنساخًا مع الوحدات الفرعية: `git clone --recurse-submodules ...`، ثم تعيين `sdk.dir` في `local.properties`، وفق `docs/BUILDING.md`.
- أمر بناء APK للاختبار على جهاز: `./gradlew :smarttubetv:assembleStmobileDebug`. وأمر بناء الإصدار الموقّع: `./gradlew :smarttubetv:assembleStmobileRelease` (يتطلب إعداد التوقيع الخاص). يعرض الدليل مخرجات APK لكل ABI ونسخة universal، وتعليمة تثبيت تستخدم `adb -s DEVICE_SERIAL`.
- توجد اختبارات JVM في `smarttubetv/src/test/` واختبارات Android في `smarttubetv/src/androidTest/`، إضافة إلى اختبارات أدوات Python مثل `tools/test_playback_benchmark.py`. لم أضف أمر اختبار غير موثق في دليل البناء.
- ` .github/workflows/CI.yml` (المسار الفعلي `.github/workflows/CI.yml`) يشغّل فحص `lintStbetaRelease` ثم `clean assembleStbetaRelease` على Ubuntu باستخدام JDK 17، مع تهيئة الوحدات الفرعية. مشغل الدفع في الملف مقيّد بفرع `master` بينما الفرع الافتراضي الحالي هو `main`؛ كما أن workflow يتضمن صياغة ومخرجات flavors لا تتطابق بوضوح مع أوامر `stmobile` في دليل البناء. يلزم التحقق من تشغيله الفعلي قبل اعتباره بوابة CI مضمونة.

### ملاحظات للمطور الجديد ونقاط غير محسومة
ابدأ بـ`docs/BUILDING.md` ثم `docs/mobile-port/HANDOFF.md` إذا كان العمل على اللاعب أو الشبكة، وراجع ملاحظات `docs/releases/` عند تتبع تغييرات نسخة بعينها. تأكد من تهيئة الوحدات الفرعية قبل البناء. لم تكن مجلدا `SharedModules` و`MediaServiceCore` موجودين في نسخة الفحص المستنسخة، لذا لم أتحقق محليًا من البناء أو من قيم التبعيات التي تأتي من ملفات الثوابت الخارجية. كما أن README العربي لا يطابق README الإنجليزي/الشفرة الحالية، وتوجد فجوة بين فرع CI المذكور والفرع الافتراضي؛ هذه نقاط تستحق التحقق لا افتراض حلها.

## English

### Purpose
According to `README.md` and `docs/BUILDING.md`, this repository is NewTube, an unofficial YouTube client for Android phones and tablets, based on SmartTube modules with a touch interface and a Media3 player. The README describes code-based sign-in, background playback, offline saving, and casting to SmartTube; these are product claims in the README, not independent verification of every feature. The current documented target is phones: `docs/BUILDING.md` says the TV flavors were removed. The older `README_AR.md`, which describes ZTube/Android TV, does not match the current project description.

### Components and main flow
- `smarttubetv` is the main Android app. The phone UI source is under `smarttubetv/src/stmobile/`; shared Android source and resources are under `smarttubetv/src/main/`.
- `common` contains shared SmartTube logic, including presenters, settings, and account plumbing, as described by `docs/BUILDING.md` and `common/build.gradle`.
- Additional Gradle modules are `filepicker-lib`, `doubletapplayerview-media3`, `slidableactivity`, `macrobenchmark`, and `sabr-media3` (listed in `settings.gradle`). Project configuration also depends on the `SharedModules` and `MediaServiceCore` submodules, as shown by `.gitmodules` and `settings.gradle`.
- The documented developer path is to clone with submodules, point Gradle at the Android SDK, and build the phone variant; installation details are in `docs/BUILDING.md`. `README.md` describes sign-in from the app UI but does not document the internal implementation flow.

### Short map
```text
.github/workflows/   CI, release, and automation workflows
common/              Shared SmartTube logic and resources
smarttubetv/         Android app; src/stmobile is phone UI, src/main shared sources
filepicker-lib/      File-picker module
sabr-media3/         SABR module based on Media3
 doubletapplayerview-media3/, slidableactivity/, macrobenchmark/ helper modules
docs/BUILDING.md     Build prerequisites, build and installation commands
docs/mobile-port/    Phone-port status and player/network engineering notes
docs/releases/       Release notes
tools/               Helper tools and related Python tests
README.md            Project, feature, and download description
README_AR.md         Existing Arabic file; describes ZTube/Android TV and conflicts with current project files
settings.gradle      Gradle modules and submodule paths
build.gradle         Android Gradle Plugin version, repositories, dependency resolution
gradle/wrapper/      Gradle Wrapper version
```

### Key files
- `settings.gradle`: includes Gradle modules and searches for `SharedModules` and `MediaServiceCore` in the checkout or beside it.
- `smarttubetv/build.gradle` and `common/build.gradle`: app/library configuration, dependencies, and test configuration.
- `gradle/wrapper/gradle-wrapper.properties`: Gradle Wrapper 9.6.1.
- `docs/BUILDING.md`: documented build and installation requirements and commands.
- `.github/workflows/CI.yml`: GitHub Actions validation/build workflow.
- `docs/mobile-port/STATUS.md` and `docs/mobile-port/HANDOFF.md`: status and engineering context for the phone port, player, and network path; the build guide recommends reading HANDOFF before changing those areas.

### Technology, versions, and configuration
- Android/Gradle: Java 17, Android Gradle Plugin 9.2.1 in `build.gradle`, and Gradle 9.6.1 in the wrapper. `docs/BUILDING.md` calls for Android SDK 37; `smarttubetv/build.gradle` reads SDK values from shared module constants.
- The app uses AndroidX, Material Components, Media3, and shared SmartTube modules, as shown in `smarttubetv/build.gradle`. The phone flavor in that file declares app version 1.15.0, application ID `io.github.aleixrodriala.arc`, and minimum API 24. Other source variants/flavors have different settings; do not assume every variant uses these values.
- `LICENSE` declares the MIT License.
- Documented local setup: `sdk.dir` in `local.properties`. The distributable release build may require the private `keystore.properties` file, according to `docs/BUILDING.md`; do not put secret values or keys in this profile or repository.
- Configuration variable/secret names referenced by files: `NEWTUBE_BENCH_DIR` and `NEWTUBE_BENCH_LABEL` (CPU benchmark tests), plus `SIGNING_KEY`, `KEY_STORE_PASSWORD`, `ALIAS`, `KEY_PASSWORD`, and `VIRUS_TOTAL_API_KEY` (CI workflow). No values are included.

### Build, tests, and CI
- The documented build requires JDK 17 and Android SDK 37 and a checkout with submodules: `git clone --recurse-submodules ...`, then set `sdk.dir` in `local.properties`, as stated in `docs/BUILDING.md`.
- The documented debug APK command for device testing is `./gradlew :smarttubetv:assembleStmobileDebug`. The signed distribution command is `./gradlew :smarttubetv:assembleStmobileRelease` (requires private signing configuration). The guide lists per-ABI and universal APK output locations and an install command using `adb -s DEVICE_SERIAL`.
- JVM tests exist in `smarttubetv/src/test/`; Android tests are in `smarttubetv/src/androidTest/`; tool tests include `tools/test_playback_benchmark.py`. No undocumented test command is asserted here.
- `.github/workflows/CI.yml` runs `lintStbetaRelease` followed by `clean assembleStbetaRelease` on Ubuntu with JDK 17 and initializes submodules. Its push trigger names `master`, while the current default branch is `main`; the workflow also uses flavor/output conventions that do not obviously match the guide's `stmobile` commands. Verify actual execution before treating this workflow as a guaranteed CI gate.

### Practical notes and open questions
Start with `docs/BUILDING.md`; read `docs/mobile-port/HANDOFF.md` before player or network work, and consult `docs/releases/` when tracing a specific release. Ensure submodules are initialized before building. The `SharedModules` and `MediaServiceCore` directories were absent from the inspected clone, so I could not locally verify the build or dependency values supplied by their external constants files. The Arabic README conflicts with the English README/current source, and the CI trigger branch differs from the current default branch; both are items to verify rather than assume resolved.
