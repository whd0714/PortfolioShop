package portfolio.shop.cart;

public interface CartRepositoryCustom {

    Cart findByOrderItemAndGoods(Long memberId);

}
