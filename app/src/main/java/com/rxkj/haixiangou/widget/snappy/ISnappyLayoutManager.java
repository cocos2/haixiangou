package com.rxkj.haixiangou.widget.snappy;

/**
 * 创建时间:   <br>
 * 作者:  <br>
 * 描述:
 */
public interface ISnappyLayoutManager {
  /**
   * @return the resultant position from a fling of the given velocity.
   */
  int getPositionForVelocity(int velocityX, int velocityY);

  /**
   * @return the position this list must scroll to to fix a state where the
   * views are not snapped to grid.
   */
  int getFixScrollPos();
}
