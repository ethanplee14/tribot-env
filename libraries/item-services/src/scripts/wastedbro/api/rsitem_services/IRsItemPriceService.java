package scripts.wastedbro.api.rsitem_services;

import java.util.Optional;

/**
 * Provides an interface for classes that can fetch RSItem pricing data
 */
public interface IRsItemPriceService
{
    Optional<Integer> tryGetPrice(int itemId);

    Optional<String> tryGetName(int itemId);

    Optional<Boolean> tryGetMembers(int itemId);
}
