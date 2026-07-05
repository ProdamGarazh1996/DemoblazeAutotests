package objects.category;

import java.util.ArrayList;

public class CategoryItemGenerator {
    public static ArrayList<CategoryItem> generateExpectedNotebookCategoryItems() {
        ArrayList<CategoryItem> categoryItemDTOS = new ArrayList<>();
        categoryItemDTOS.add(new CategoryItem("Sony vaio i5", "notebook", 8, "imgs/sony_vaio_5.jpg", 790.0, "Sony is so confident that the VAIO S is a superior ultraportable laptop \nthat the company proudly compares the notebook to Apple's 13-inch \nMacBook Pro. And in a lot of ways this notebook is better, thanks to a \nlighter weight."));
        categoryItemDTOS.add(new CategoryItem("Sony vaio i7\n", "notebook", 9, "imgs/sony_vaio_5.jpg", 790.0, "REVIEW\n \nSony is so confident that the VAIO S is a superior \nultraportable laptop that the company proudly compares the notebook to \nApple's 13-inch MacBook Pro. And in a lot of ways this notebook is \nbetter, thanks to a lighter weight, higher-resolution display, more \nstorage space, and a Blu-ray drive. "));
        categoryItemDTOS.add(new CategoryItem("MacBook air", "notebook", 11, "imgs/macbook_air.jpg", 700.0, "1.6GHz dual-core Intel Core i5 (Turbo Boost up to 2.7GHz) with 3MB \nshared L3 cache\nConfigurable to 2.2GHz dual-core Intel Core i7 (Turbo \nBoost up to 3.2GHz) with 4MB shared L3 cache."));
        categoryItemDTOS.add(new CategoryItem("Dell i7 8gb", "notebook", 12, "imgs/dell.jpg", 700.0, "6th Generation Intel Core i7-6500U Dual-Core Processor 2.5 GHz (max \nboost speed up to 3.1GHz) 4MB L3 Cache, 8GB DDR4 1600 MHz, 1TB 5400 RPM \nHDD15.6 in Full HD LED-backlit touchscreen with Truelife (1920 x 1080), \n10-finger multi-touch support, Intel HD Graphics 520 with shared \ngraphics memory"));
        categoryItemDTOS.add(new CategoryItem("2017 Dell 15.6 Inch", "notebook", 13, "imgs/dell.jpg", 700.0, "7th Gen Intel Core i7-7500U mobile processor 2.70 GHz with Turbo Boost \nTechnology up to 3.50 GHz, Intel HD Graphics 62015.6 inch Full HD IPS \nTrueLife LED-backlit touchscreen (1920 x 1080), 10-finger multi-touch \nsupport, 360° flip-and-fold design,8GB DDR4 2400 MHz Memory, 1TB 5400 \nRPM HDD, No optical drive, 3 in 1 card reader (SD SDHC SDXC)"));
        categoryItemDTOS.add(new CategoryItem("MacBook Pro", "notebook", 15, "imgs/macbook_air.jpg", 1100.0, "Apple has introduced three new versions of its MacBook Pro line, \nincluding a 13-inch and 15-inch model with the Touch Bar, a thin, \nmulti-touch strip display that sits above the MacBook Pro's keyboard. "));

        return categoryItemDTOS;
    }

    public static ArrayList<CategoryItem> generateExpectedPhoneCategoryItems() {
        ArrayList<CategoryItem> categoryItemDTOS = new ArrayList<>();
        categoryItemDTOS.add(new CategoryItem("Samsung galaxy s6", "phone",1, "imgs/galaxy_s6.jpg", 360.0, "The Samsung Galaxy S6 is powered by 1.5GHz octa-core Samsung Exynos 7420\n processor and it comes with 3GB of RAM. The phone packs 32GB of \ninternal storage cannot be expanded. "));
        categoryItemDTOS.add(new CategoryItem("Nokia lumia 1520", "phone", 2, "imgs/Lumia_1520.jpg", 820.0, "The Nokia Lumia 1520 is powered by 2.2GHz quad-core Qualcomm Snapdragon 800 processor and it comes with 2GB of RAM. "));
        categoryItemDTOS.add(new CategoryItem("Nexus 6", "phone",3, "imgs/Nexus_6.jpg", 650.0, "The Motorola Google Nexus 6 is powered by 2.7GHz quad-core Qualcomm Snapdragon 805 processor and it comes with 3GB of RAM."));
        categoryItemDTOS.add(new CategoryItem("Samsung galaxy s7", "phone", 4, "imgs/galaxy_s6.jpg", 800.0, "The Samsung Galaxy S7 is powered by 1.6GHz octa-core it comes with 4GB \nof RAM. The phone packs 32GB of internal storage that can be expanded up\n to 200GB via a microSD card."));
        categoryItemDTOS.add(new CategoryItem("Iphone 6 32gb", "phone", 5, "imgs/iphone_6.jpg", 790.0, "It comes with 1GB of RAM. The phone packs 16GB of internal storage \ncannot be expanded. As far as the cameras are concerned, the Apple \niPhone 6 packs a 8-megapixel primary camera on the rear and a \n1.2-megapixel front shooter for selfies."));
        categoryItemDTOS.add(new CategoryItem("Sony xperia z5", "phone", 6, "imgs/xperia_z5.jpg", 320.0, "Sony Xperia Z5 Dual smartphone was launched in September 2015. The phone\n comes with a 5.20-inch touchscreen display with a resolution of 1080 \npixels by 1920 pixels at a PPI of 424 pixels per inch."));
        categoryItemDTOS.add(new CategoryItem("HTC One M9", "phone", 7, "imgs/HTC_M9.jpg", 700.0, "The HTC One M9 is powered by 1.5GHz octa-core Qualcomm Snapdragon 810 \nprocessor and it comes with 3GB of RAM. The phone packs 32GB of internal\n storage that can be expanded up to 128GB via a microSD card. "));

        return categoryItemDTOS;
    }

    public static ArrayList<CategoryItem> generateExpectedMonitorCategoryItems() {
        ArrayList<CategoryItem> categoryItemDTOS = new ArrayList<>();
        categoryItemDTOS.add(new CategoryItem("Apple monitor 24", "monitor", 10, "imgs/apple_cinema.jpg", 400.0, "LED Cinema Display features a 27-inch glossy LED-backlit TFT \nactive-matrix LCD display with IPS technology and an optimum resolution \nof 2560x1440. It has a 178 degree horizontal and vertical viewing angle,\n a \"typical\" brightness of 375 cd/m2, contrast ratio of 1000:1, and a \n12 ms response time."));
        categoryItemDTOS.add(new CategoryItem("ASUS Full HD", "monitor", 14, "imgs/asusm.jpg", 230.0, "ASUS VS247H-P 23.6- Inch Full HD"));

        return categoryItemDTOS;
    }

    public static CategoryItem generateExpectedPhoneCategoryItem() {
        return new CategoryItem("Samsung galaxy s6", "phone",1, "imgs/galaxy_s6.jpg", 360.0, "The Samsung Galaxy S6 is powered by 1.5GHz octa-core Samsung Exynos 7420\n processor and it comes with 3GB of RAM. The phone packs 32GB of \ninternal storage cannot be expanded. ");
    }

    public static CategoryItem generateExpectedNotebookCategoryItem() {
        return new CategoryItem("Sony vaio i5", "notebook", 8, "imgs/sony_vaio_5.jpg", 790.0, "Sony is so confident that the VAIO S is a superior ultraportable laptop \nthat the company proudly compares the notebook to Apple's 13-inch \nMacBook Pro. And in a lot of ways this notebook is better, thanks to a \nlighter weight.");
    }

    public static CategoryItem generateExpectedMonitorCategoryItem() {
        return new CategoryItem("Apple monitor 24", "monitor", 10, "imgs/apple_cinema.jpg", 400.0, "LED Cinema Display features a 27-inch glossy LED-backlit TFT \nactive-matrix LCD display with IPS technology and an optimum resolution \nof 2560x1440. It has a 178 degree horizontal and vertical viewing angle,\n a \"typical\" brightness of 375 cd/m2, contrast ratio of 1000:1, and a \n12 ms response time.");
    }

    public static ArrayList<CategoryItem> generateExpectedCategoryItemsByPagination() {
        ArrayList<CategoryItem> categoryItemDTOS = new ArrayList<>();
        categoryItemDTOS.add(new CategoryItem("Nokia lumia 1520", "phone", 2, "imgs/Lumia_1520.jpg", 820.0, "The Nokia Lumia 1520 is powered by 2.2GHz quad-core Qualcomm Snapdragon 800 processor and it comes with 2GB of RAM. "));
        categoryItemDTOS.add(new CategoryItem("Nexus 6", "phone",3, "imgs/Nexus_6.jpg", 650.0, "The Motorola Google Nexus 6 is powered by 2.7GHz quad-core Qualcomm Snapdragon 805 processor and it comes with 3GB of RAM."));
        categoryItemDTOS.add(new CategoryItem("Samsung galaxy s7", "phone", 4, "imgs/galaxy_s6.jpg", 800.0, "The Samsung Galaxy S7 is powered by 1.6GHz octa-core it comes with 4GB \nof RAM. The phone packs 32GB of internal storage that can be expanded up\n to 200GB via a microSD card."));
        categoryItemDTOS.add(new CategoryItem("Iphone 6 32gb", "phone", 5, "imgs/iphone_6.jpg", 790.0, "It comes with 1GB of RAM. The phone packs 16GB of internal storage \ncannot be expanded. As far as the cameras are concerned, the Apple \niPhone 6 packs a 8-megapixel primary camera on the rear and a \n1.2-megapixel front shooter for selfies."));
        categoryItemDTOS.add(new CategoryItem("Sony xperia z5", "phone", 6, "imgs/xperia_z5.jpg", 320.0, "Sony Xperia Z5 Dual smartphone was launched in September 2015. The phone\n comes with a 5.20-inch touchscreen display with a resolution of 1080 \npixels by 1920 pixels at a PPI of 424 pixels per inch."));
        categoryItemDTOS.add(new CategoryItem("HTC One M9", "phone", 7, "imgs/HTC_M9.jpg", 700.0, "The HTC One M9 is powered by 1.5GHz octa-core Qualcomm Snapdragon 810 \nprocessor and it comes with 3GB of RAM. The phone packs 32GB of internal\n storage that can be expanded up to 128GB via a microSD card. "));
        categoryItemDTOS.add(new CategoryItem("Sony vaio i5", "notebook", 8, "imgs/sony_vaio_5.jpg", 790.0, "Sony is so confident that the VAIO S is a superior ultraportable laptop \nthat the company proudly compares the notebook to Apple's 13-inch \nMacBook Pro. And in a lot of ways this notebook is better, thanks to a \nlighter weight."));
        categoryItemDTOS.add(new CategoryItem("Sony vaio i7\n", "notebook", 9, "imgs/sony_vaio_5.jpg", 790.0, "REVIEW\n \nSony is so confident that the VAIO S is a superior \nultraportable laptop that the company proudly compares the notebook to \nApple's 13-inch MacBook Pro. And in a lot of ways this notebook is \nbetter, thanks to a lighter weight, higher-resolution display, more \nstorage space, and a Blu-ray drive. "));
        categoryItemDTOS.add(new CategoryItem("Apple monitor 24", "monitor", 10, "imgs/apple_cinema.jpg", 400.0, "LED Cinema Display features a 27-inch glossy LED-backlit TFT \nactive-matrix LCD display with IPS technology and an optimum resolution \nof 2560x1440. It has a 178 degree horizontal and vertical viewing angle,\n a \"typical\" brightness of 375 cd/m2, contrast ratio of 1000:1, and a \n12 ms response time."));

        return categoryItemDTOS;
    }
}
